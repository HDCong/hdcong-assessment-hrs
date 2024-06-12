package com.hrs.demo.command.web.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
  private int code;


  public BaseException(String message) {
    super(message);
  }

  public BaseException(String message, int code) {
    super(message);
    this.code = code;
  }
}