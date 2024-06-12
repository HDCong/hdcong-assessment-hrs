package com.hrs.demo.query.repository;

import com.hrs.demo.query.entities.Hotel;
import com.hrs.demo.query.web.dto.request.GetAllHotelCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IHotelRepositoryCustom {
    Page<Hotel> search(GetAllHotelCriteria criteria, Pageable pageable);
}
