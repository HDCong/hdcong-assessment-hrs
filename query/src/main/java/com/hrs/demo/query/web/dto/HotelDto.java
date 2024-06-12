package com.hrs.demo.query.web.dto;

import java.util.List;
import lombok.Data;

@Data
public class HotelDto {

  private Long id;
  private String name;
  private String description;
  private AddressDto address;
  private List<RoomDto> roomTypes;

}
