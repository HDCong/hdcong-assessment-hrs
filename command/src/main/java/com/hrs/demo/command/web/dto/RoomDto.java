package com.hrs.demo.command.web.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class RoomDto {

  private Long id;
  private String name;
  private BigDecimal basePrice;
  private List<String> images;
  private int remainingRooms;

}
