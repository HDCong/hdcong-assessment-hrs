package com.hrs.demo.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.hrs.demo.command.entities.Booking;
import com.hrs.demo.command.entities.RoomType;
import com.hrs.demo.command.entities.User;
import com.hrs.demo.command.entities.enums.BookingStatus;
import com.hrs.demo.command.repository.IBookingRepository;
import com.hrs.demo.command.repository.IRoomAvailabilityByDateRepository;
import com.hrs.demo.command.repository.IRoomTypeRepository;
import com.hrs.demo.command.repository.IUserRepository;
import com.hrs.demo.command.service.implementation.BookingService;
import com.hrs.demo.command.service.implementation.dummy.DummyProducerService;
import com.hrs.demo.command.web.dto.request.BookingRequest;
import com.hrs.demo.command.web.exceptions.BadRequestException;
import com.hrs.demo.command.web.exceptions.DataNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BookingServiceTest {

  @InjectMocks
  private BookingService bookingService;

  @Mock
  private IRoomTypeRepository roomTypeRepository;

  @Mock
  private IRoomAvailabilityByDateRepository roomAvailabilityByDateRepository;

  @Mock
  private IUserRepository userRepository;

  @Mock
  private IBookingRepository bookingRepository;

  @Mock
  private DummyProducerService dummyProducerService;

  public BookingServiceTest() {
    MockitoAnnotations.openMocks(this);
  }
//
//  @Test
//  void whenCreateBooking_thenReturnBooking() {
//
//    final var hotel = Hotel.builder()
//        .id(1L)
//        .name("Hotel California")
//        .location("Los Angeles")
//        .description("A lovely place")
//        .build();
//    final var validRoom = Room.builder()
//        .id(1L)
//        .hotel(hotel)
//        .pricePerNight(100D)
//        .roomNumber("101")
//        .roomType("DELUXE")
//        .bookings(new ArrayList<>())
//        .build();
//    hotel.setRooms(List.of(validRoom));
//
//    LocalDate nowDate = LocalDate.now();
//
//    final var bookingReq = BookingRequest.builder()
//        .roomId(1L)
//        .startDate(nowDate)
//        .endDate(nowDate.plusDays(10))
//        .userId("user1")
//        .build();
//
//    final var mockBookingResult = Booking.builder()
//        .id(1L)
//        .room(validRoom)
//        .status(BookingStatus.INIT)
//        .startDate(nowDate)
//        .endDate(nowDate.plusDays(10))
//        .build();
//    Mockito.when(roomRepository.findById(any(Long.class))).thenReturn(Optional.of(validRoom));
//    Mockito.when(bookingRepository.save(any(Booking.class))).thenReturn(mockBookingResult);
//
//    final var result = bookingService.createBooking(bookingReq);
//
//    assertNotNull(result);
//    assertEquals(result.getId(), 1L);
//    assertEquals(result.getStartDate(), nowDate);
//  }
//
//  @Test
//  void whenCreateBookingWithRoomNotExisted_thenThrowException() throws BadRequestException {
//
//    LocalDate nowDate = LocalDate.now();
//
//    final var bookingReq = BookingRequest.builder()
//        .roomId(1L)
//        .startDate(nowDate)
//        .endDate(nowDate.plusDays(10))
//        .userId("user1")
//        .build();
//    Mockito.when(roomRepository.findById(any(Long.class))).thenReturn(Optional.empty());
//
//    Assertions.assertThrows(BadRequestException.class,
//        () -> bookingService.createBooking(bookingReq));
//  }
//
//  @Test
//  void whenCreateBookingWithInvalidRoom_thenThrowException() throws BadRequestException {
//    LocalDate nowDate = LocalDate.now();
//
//    final var hotel = Hotel.builder()
//        .id(1L)
//        .name("Hotel California")
//        .location("Los Angeles")
//        .description("A lovely place")
//        .build();
//    final var bookedInfo = Booking.builder()
//        .id(1L)
//        .status(BookingStatus.INIT)
//        .startDate(nowDate)
//        .endDate(nowDate.plusDays(20))
//        .build();
//    final var invalidRoom = Room.builder()
//        .id(1L)
//        .hotel(hotel)
//        .pricePerNight(100D)
//        .roomNumber("101")
//        .roomType("DELUXE")
//        .build();
//    bookedInfo.setRoom(invalidRoom);
//    invalidRoom.setBookings(List.of(bookedInfo));
//    hotel.setRooms(List.of(invalidRoom));
//
//    final var bookingReq = BookingRequest.builder()
//        .roomId(1L)
//        .startDate(nowDate)
//        .endDate(nowDate.plusDays(10))
//        .userId("user1")
//        .build();
//
//    final var mockBookingResult = Booking.builder()
//        .id(1L)
//        .room(invalidRoom)
//        .status(BookingStatus.INIT)
//        .startDate(nowDate)
//        .endDate(nowDate.plusDays(10))
//        .build();
//    Mockito.when(roomRepository.findById(any(Long.class))).thenReturn(Optional.of(invalidRoom));
//
//    Assertions.assertThrows(
//        BadRequestException.class,
//        () -> bookingService.createBooking(bookingReq));
//  }

  @Test
  public void whenCreateBooking_thenReturnBookingCreated() {
    // Mocking the request
    BookingRequest request = BookingRequest
        .builder()
        .startDate(LocalDate.now())
        .endDate(LocalDate.now().plusDays(2))
        .roomTypeIds(List.of(1L, 2L))
        .userId(1L)
        .build();
    // Mocking room types
    RoomType roomType1 = new RoomType();
    roomType1.setId(1L);
    roomType1.setBasePrice(BigDecimal.TEN);
    roomType1.setTotalRoom(100);

    RoomType roomType2 = new RoomType();
    roomType2.setId(2L);
    roomType2.setBasePrice(BigDecimal.valueOf(15));
    roomType2.setTotalRoom(121);

    Mockito.when(roomTypeRepository.findAllById(ArgumentMatchers.anyList()))
        .thenReturn(List.of(roomType1, roomType2));

    // Mocking userRepository
    User user = User.builder().id(1L).build();
    Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

    // Mocking bookingRepository
    Mockito.when(bookingRepository.save(any(Booking.class))).thenAnswer(invocation -> {
      Booking savedBooking = invocation.getArgument(0);
      savedBooking.setId(1L);
      return savedBooking;
    });

    var bookingDto = bookingService.createBooking(request);

    assertEquals(Double.valueOf(50), bookingDto.getTotalBasePrice());
  }

  @Test
  void testCreateBooking_WithInvalidRoomTypeIds_ShouldThrowBadRequestException() {
    // Prepare test data
    BookingRequest request = BookingRequest.builder().build();

    // Perform the test and verify the exception
    Assertions.assertThrows(BadRequestException.class, () -> bookingService.createBooking(request));
  }

  @Test
  void testCreateBooking_WithInsufficientRoomAvailability_ShouldThrowBadRequestException() {
    // Prepare test data
    BookingRequest request = BookingRequest.builder()
        .roomTypeIds(List.of(1L))
        .startDate(LocalDate.now())
        .endDate(LocalDate.now().plusDays(2))
        .build();
    RoomType roomType = new RoomType();
    roomType.setId(1L);
    roomType.setTotalRoom(0); // No available rooms

    Mockito.when(roomTypeRepository.findAllById(request.getRoomTypeIds()))
        .thenReturn(List.of(roomType));

    // Perform the test and verify the exception
    Assertions.assertThrows(BadRequestException.class,
        () -> bookingService.createBooking(request));
  }

  @Test
  public void testUpdateBookingStatus() {
    // Mocking the booking
    Booking booking = Booking.builder().id(1L).build();
    Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
    Mockito.when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
    var bookingDto = bookingService.updateBookingStatus(1L, BookingStatus.CHECKED_IN);
    // Verify the result
    assertEquals(BookingStatus.CHECKED_IN, bookingDto.getStatus());

  }

  @Test
  void testCancelBooking_WithInvalidBookingId_ShouldThrowDataNotFoundException() {
    // Prepare test data
    Long bookingId = 1L;

    // Mock behavior
    Mockito.when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

    // Perform the test and verify the exception
    Assertions.assertThrows(DataNotFoundException.class, () -> bookingService.cancelBooking(bookingId));
  }

  @Test
  void whenCancelBook_thenSuccessIfValidateOk() {
    Long bookingId = 1L;
    Booking booking = Booking.builder().id(1L).build();
    Mockito.when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

    Assertions.assertDoesNotThrow(() -> bookingService.cancelBooking(bookingId));

  }
}
