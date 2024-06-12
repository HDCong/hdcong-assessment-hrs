package com.hrs.demo.query.service;


import com.hrs.demo.query.web.dto.HotelDto;
import com.hrs.demo.query.web.dto.request.GetAllHotelCriteria;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Pageable;

public interface IHotelService {

  /**
   * Find all hotel base on given criteria
   * @param request given criteria
   * @param page given page (size, page)
   * @return pair of total result and list of {@link HotelDto}
   */
  Pair<Long, List<HotelDto>> findAll(GetAllHotelCriteria request, Pageable page);

  /**
   * Get hotel by id
   * @param id given id
   * @return {@link HotelDto} found by id
   */
  HotelDto findById(Long id);

}
