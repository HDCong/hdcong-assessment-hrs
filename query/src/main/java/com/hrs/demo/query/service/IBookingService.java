package com.hrs.demo.query.service;

import com.hrs.demo.query.web.dto.BookingDto;
import com.hrs.demo.query.web.dto.request.GetAllBookingCriteria;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Pageable;

public interface IBookingService {

  /**
   * Find all booking info
   * @param request given criteria
   * @param page given page (size, page)
   * @return pair of total and {@link BookingDto} found
   */
  Pair<Long, List<BookingDto>> findAll(GetAllBookingCriteria request, Pageable page);

  /**
   * Find booking info
   * @param id given id
   * @return {@link BookingDto} found by id
   */
  BookingDto findById(Long id);
}
