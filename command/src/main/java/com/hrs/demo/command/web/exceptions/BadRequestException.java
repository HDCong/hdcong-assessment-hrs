package com.hrs.demo.command.web.exceptions;

public class BadRequestException extends BaseException {

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, int code) {
    super(message, code);
  }

}
