package com.hrs.demo.command.service.implementation;

import com.hrs.demo.command.entities.Booking;
import com.hrs.demo.command.entities.RoomAvailabilityByDate;
import com.hrs.demo.command.entities.RoomType;
import com.hrs.demo.command.entities.User;
import com.hrs.demo.command.entities.enums.BookingStatus;
import com.hrs.demo.command.mapper.IBookingMapper;
import com.hrs.demo.command.mapper.IUserMapper;
import com.hrs.demo.command.repository.IBookingRepository;
import com.hrs.demo.command.repository.IRoomAvailabilityByDateRepository;
import com.hrs.demo.command.repository.IRoomTypeRepository;
import com.hrs.demo.command.repository.IUserRepository;
import com.hrs.demo.command.service.IBookingService;
import com.hrs.demo.command.service.IMessageProducer;
import com.hrs.demo.command.utils.Constants;
import com.hrs.demo.command.web.dto.BookingDto;
import com.hrs.demo.command.web.dto.request.BookingRequest;
import com.hrs.demo.command.web.dto.request.BookingUpdateRequest;
import com.hrs.demo.command.web.exceptions.BadRequestException;
import com.hrs.demo.command.web.exceptions.DataNotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

  private final IBookingRepository bookingRepository;
  private final IMessageProducer messageProducer;
  private final IRoomTypeRepository roomTypeRepository;
  private final IRoomAvailabilityByDateRepository roomAvailabilityByDateRepository;
  private final IUserRepository userRepository;

  @PostConstruct
  public void syncData() {
    // only for demo purpose, to sync init data to elastic search.
    bookingRepository.findAll().forEach((booking) -> {
      booking.getRoomTypes().forEach((roomType) -> {
        roomType.getHotel();
      });
      messageProducer.sendMessage(Constants.BOOKING_TOPIC, booking);;
    });
  }
  @Override
  @Transactional
  public BookingDto createBooking(final BookingRequest request) {
    // Validate room types
    if (CollectionUtils.isEmpty(request.getRoomTypeIds())) {
      throw new BadRequestException("RoomTypeIds is empty");
    }
    final User user = validateAndGetUser(request);
    final List<RoomType> roomTypes = roomTypeRepository.findAllById(request.getRoomTypeIds());
    if (roomTypes.size() != request.getRoomTypeIds().size()) {
      throw new BadRequestException("One or more room types not found");
    }
    final List<RoomAvailabilityByDate> toUpdateRooms = new ArrayList<>();
    for (var roomType : roomTypes) {
      LocalDate date = request.getStartDate();
      while (!date.isAfter(request.getEndDate())) {
        LocalDate thisDate = date;
        var availableRooms = roomAvailabilityByDateRepository.findByRoomTypeAndDate(
                roomType,
                date)
            .orElseGet(() -> RoomAvailabilityByDate
                .builder()
                .roomRemains(roomType.getTotalRoom())
                .date(thisDate)
                .roomType(roomType)
                .build());
        if (availableRooms.getRoomRemains() < 1) {
          throw new IllegalArgumentException(
              "Room type " + roomType.getId() + " does not have enough availability on " + date);
        }

        availableRooms.setRoomRemains(availableRooms.getRoomRemains() - 1);
        toUpdateRooms.add(availableRooms);
        date = date.plusDays(1);
      }
    }
    roomAvailabilityByDateRepository.saveAll(toUpdateRooms);

    final var totalBasePrice = roomTypes.stream()
        .map(RoomType::getBasePrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add)
        .multiply(BigDecimal.valueOf(
            ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate())));

    final Booking booking = IBookingMapper.INSTANCE.toEntity(
        request,
        user,
        totalBasePrice,
        roomTypes);

    final var savedBooking = bookingRepository.save(booking);
    messageProducer.sendMessage(Constants.BOOKING_TOPIC, savedBooking);
    return IBookingMapper.INSTANCE.toDto(savedBooking);
  }

  @Override
  public BookingDto updateBookingStatus(final Long id, BookingStatus status) {
    final Booking booking = bookingRepository.findById(id)
        .orElseThrow(() -> new DataNotFoundException(Booking.class.getSimpleName(), id));
    booking.setStatus(status);
    final Booking updatedBooking = bookingRepository.save(booking);
    messageProducer.sendMessage(Constants.BOOKING_TOPIC, updatedBooking);
    return IBookingMapper.INSTANCE.toDto(updatedBooking);
  }

  @Override
  @Transactional
  public void cancelBooking(Long id) {
    // validate ...
    updateBookingStatus(id, BookingStatus.CANCELED);
  }

  @Override
  public void updateBooking(Long id, BookingUpdateRequest request) {
    // validate...
    final Booking booking = bookingRepository.findById(id)
        .orElseThrow(() -> new DataNotFoundException(Booking.class.getSimpleName(), id));
    /// do stuff update booking.

    messageProducer.sendMessage(Constants.BOOKING_TOPIC, booking);
  }

  @Transactional
  protected User validateAndGetUser(BookingRequest request) {
    if (Objects.nonNull(request.getUserId())) {
      return userRepository.findById(request.getUserId())
          .orElseThrow(() -> new BadRequestException("User not found"));
    }
    if (StringUtils.isBlank(request.getPhoneNumber())) {
      throw new BadRequestException("Phone number is required for anonymous request");
    }
    return userRepository
        .findByPhoneNumber(request.getPhoneNumber())
        .orElseGet(() -> userRepository.save(IUserMapper.INSTANCE.toEntity(request)));
  }
}
