package com.hrs.demo.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hrs.demo.query.entities.Booking;
import com.hrs.demo.query.mapper.IBookingMapper;
import com.hrs.demo.query.repository.IBookingRepository;
import com.hrs.demo.query.repository.IBookingRepositoryCustom;
import com.hrs.demo.query.service.implementation.BookingService;
import com.hrs.demo.query.web.dto.BookingDto;
import com.hrs.demo.query.web.dto.request.GetAllBookingCriteria;
import com.hrs.demo.query.web.exceptions.BadRequestException;
import com.hrs.demo.query.web.exceptions.DataNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BookingServiceTest {

  @Mock
  private IBookingRepository bookingRepository;

  @Mock
  private IBookingRepositoryCustom bookingRepositoryCustom;

  @InjectMocks
  private BookingService bookingService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void findById_whenBookingExists_shouldReturnBookingDto() {
    Booking booking = new Booking();
    booking.setId(1L);

    when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));

    BookingDto result = bookingService.findById(1L);

    assertNotNull(result);
    verify(bookingRepository, times(1)).findById(1L);
  }

  @Test
  void findById_whenBookingDoesNotExist_shouldThrowDataNotFoundException() {
    when(bookingRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(DataNotFoundException.class, () -> bookingService.findById(1L));
    verify(bookingRepository, times(1)).findById(1L);
  }

  @Test
  void findAll_whenUserIdIsBlank_shouldThrowBadRequestException() {
    GetAllBookingCriteria criteria = new GetAllBookingCriteria();
    criteria.setUserId("");

    Pageable pageable = PageRequest.of(0, 10);

    assertThrows(BadRequestException.class, () -> bookingService.findAll(criteria, pageable));
  }

  @Test
  void findAll_whenBookingsExist_shouldReturnBookingDtos() {
    GetAllBookingCriteria criteria = new GetAllBookingCriteria();
    criteria.setUserId("user123");

    Pageable pageable = PageRequest.of(0, 10);
    List<Booking> bookings = Collections.singletonList(new Booking());
    Page<Booking> bookingPage = new PageImpl<>(bookings);

    when(bookingRepositoryCustom.search(any(GetAllBookingCriteria.class),
        any(Pageable.class))).thenReturn(bookingPage);

    Pair<Long, List<BookingDto>> result = bookingService.findAll(criteria, pageable);

    assertEquals(1, result.getLeft());
    assertEquals(1, result.getRight().size());
    verify(bookingRepositoryCustom, times(1)).search(criteria, pageable);
  }

  @Test
  void findAll_whenNoBookingsExist_shouldReturnEmptyList() {
    GetAllBookingCriteria criteria = new GetAllBookingCriteria();
    criteria.setUserId("user123");

    Pageable pageable = PageRequest.of(0, 10);
    Page<Booking> bookingPage = Page.empty();

    when(bookingRepositoryCustom.search(any(GetAllBookingCriteria.class),
        any(Pageable.class))).thenReturn(bookingPage);

    Pair<Long, List<BookingDto>> result = bookingService.findAll(criteria, pageable);

    assertEquals(0, result.getLeft());
    assertTrue(result.getRight().isEmpty());
    verify(bookingRepositoryCustom, times(1)).search(criteria, pageable);
  }

}
