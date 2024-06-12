package com.hrs.demo.command.web.dto;

import com.hrs.demo.command.entities.enums.BookingStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class BookingDto {

  private Long id;
  private String userId;
  private LocalDate startDate;
  private LocalDate endDate;
  private Double totalBasePrice;
  private BookingStatus status;
  private List<RoomDto> rooms;


}
