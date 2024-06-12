package com.hrs.demo.query.web.dto.request;

import lombok.Data;

@Data
public class GetAllBookingCriteria {
  private String keyword;
  private String userId;
}
