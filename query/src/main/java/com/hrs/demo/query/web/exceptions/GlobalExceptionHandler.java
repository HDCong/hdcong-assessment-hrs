package com.hrs.demo.query.web.exceptions;

import com.hrs.demo.query.web.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Transactional(rollbackFor = Throwable.class)
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleException(final Exception exception) {
    log.error(exception.getMessage(), exception);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ErrorDto.builder()
            .message(exception.getMessage())
            .build());
  }

  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<ErrorDto> handleDataNotFoundException(final Exception exception) {
    log.error(exception.getMessage(), exception);

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ErrorDto.builder()
            .message(exception.getMessage())
            .build());
  }
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorDto> handleBadRequestException(final Exception exception) {
    log.error(exception.getMessage(), exception);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorDto.builder()
            .message(exception.getMessage())
            .build());
  }


}
