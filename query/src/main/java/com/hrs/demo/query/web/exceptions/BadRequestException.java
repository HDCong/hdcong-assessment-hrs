package com.hrs.demo.query.web.exceptions;

public class BadRequestException extends BaseException {

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, int code) {
    super(message, code);
  }

}
