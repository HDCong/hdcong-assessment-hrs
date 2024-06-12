package com.hrs.demo.command.web.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDto {
  private String message;
  @Builder.Default
  private String resultCode = "-1";
}
