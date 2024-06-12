package com.hrs.demo.command.service;


import com.hrs.demo.command.web.dto.HotelDto;
import com.hrs.demo.command.web.dto.request.CreateHotelRequest;

public interface IHotelService {

  /**
   * Create hotel
   * @param hotel given request
   * @return {@link HotelDto} created
   */
  HotelDto createHotel(CreateHotelRequest hotel);
}
