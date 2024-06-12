package com.hrs.demo.command.web.controller;

import com.hrs.demo.command.service.IBookingService;
import com.hrs.demo.command.web.dto.BookingDto;
import com.hrs.demo.command.web.dto.request.BookingRequest;
import com.hrs.demo.command.web.dto.request.BookingUpdateRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/booking")
@RequiredArgsConstructor
@Tag(name = "booking-command-v1-controller")
public class BookingCommandController {

  private final IBookingService bookingService;

  @PostMapping
  public ResponseEntity<BookingDto> createBooking(@RequestBody BookingRequest request) {
    return ResponseEntity.ok(bookingService.createBooking(request));
  }

  @PutMapping("/{id}/cancel")
  public ResponseEntity<Void> cancelBooking(@PathVariable(name = "id") Long id) {
    bookingService.cancelBooking(id);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateBooking(
      @PathVariable(name = "id") Long id,
      @RequestBody BookingUpdateRequest request
  ) {
    bookingService.updateBooking(id, request);
    return ResponseEntity.ok().build();
  }
}
