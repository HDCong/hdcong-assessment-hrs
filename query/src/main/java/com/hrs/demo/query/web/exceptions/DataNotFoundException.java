package com.hrs.demo.query.web.exceptions;

public class DataNotFoundException  extends BaseException {

  public DataNotFoundException(String entity, Object id) {
    super(String.format("%s[%s] not found", entity, id));
  }

  public DataNotFoundException(String message, int code) {
    super(message, code);
  }

}