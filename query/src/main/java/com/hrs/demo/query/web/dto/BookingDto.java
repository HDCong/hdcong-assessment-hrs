package com.hrs.demo.query.web.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class BookingDto {
  private Long id;
  private List<RoomDto> roomTypes;
  private LocalDate startDate;
  private LocalDate endDate;
  private Double totalBasePrice;
}
