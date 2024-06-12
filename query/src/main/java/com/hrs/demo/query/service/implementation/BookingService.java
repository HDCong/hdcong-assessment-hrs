package com.hrs.demo.query.service.implementation;

import com.hrs.demo.query.entities.Booking;
import com.hrs.demo.query.mapper.IBookingMapper;
import com.hrs.demo.query.repository.IBookingRepository;
import com.hrs.demo.query.repository.IBookingRepositoryCustom;
import com.hrs.demo.query.service.IBookingService;
import com.hrs.demo.query.web.dto.BookingDto;
import com.hrs.demo.query.web.dto.request.GetAllBookingCriteria;
import com.hrs.demo.query.web.exceptions.BadRequestException;
import com.hrs.demo.query.web.exceptions.DataNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

  private final IBookingRepository bookingRepository;
  private final IBookingRepositoryCustom bookingRepositoryCustom;

  @Override
  public BookingDto findById(Long id) {
    return IBookingMapper.INSTANCE.toDto(
        bookingRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException(Booking.class.getSimpleName(), id)));
  }

  @Override
  public Pair<Long, List<BookingDto>> findAll(GetAllBookingCriteria criteria, Pageable page) {
    if (StringUtils.isBlank(criteria.getUserId())) {
      throw new BadRequestException("userId is empty");
    }
    final var result = bookingRepositoryCustom.search(criteria, page);
    return Pair.of(
        result.getTotalElements(),
        result.getContent()
            .stream()
            .map(IBookingMapper.INSTANCE::toDto)
            .toList()
    );
  }
}
