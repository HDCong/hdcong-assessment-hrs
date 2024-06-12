package com.hrs.demo.command.web.dto.request;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequest {

  private List<Long> roomTypeIds;
  private Long userId;
  private String phoneNumber;
  private String email;
  private String name;
  private LocalDate startDate;
  private LocalDate endDate;

}
