package com.hrs.demo.command.web.dto.request;

import com.hrs.demo.command.entities.enums.BookingStatus;
import java.time.LocalDate;
import lombok.Data;

@Data
public class BookingUpdateRequest {

  private Long bookingId;
  private BookingStatus status;
  private LocalDate startDate;
  private LocalDate endDate;

}
