package com.hrs.demo.command.service;

import com.hrs.demo.command.entities.enums.BookingStatus;
import com.hrs.demo.command.web.dto.BookingDto;
import com.hrs.demo.command.web.dto.request.BookingRequest;
import com.hrs.demo.command.web.dto.request.BookingUpdateRequest;

public interface IBookingService {

  /**
   * Create booking
   * @param request given request
   * @return {@link BookingDto} created
   */
  BookingDto createBooking(BookingRequest request);

  /**
   * Update booking status
   * @param id given id
   * @param status given new status
   * @return {@link BookingDto} updated
   */
  BookingDto updateBookingStatus(Long id, BookingStatus status);

  /**
   * Cancel booking by given id
   * @param id given id
   */
  void cancelBooking(Long id);

  void updateBooking(Long id, BookingUpdateRequest request);
}
