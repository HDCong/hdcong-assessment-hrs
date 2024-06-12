package com.hrs.demo.query.repository;

import com.hrs.demo.query.entities.Booking;
import com.hrs.demo.query.web.dto.request.GetAllBookingCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookingRepositoryCustom {

  Page<Booking> search(GetAllBookingCriteria criteria, Pageable pageable);
}
