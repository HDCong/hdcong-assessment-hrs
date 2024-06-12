package com.hrs.demo.command.web.controller;

import com.hrs.demo.command.service.IHotelService;
import com.hrs.demo.command.web.dto.HotelDto;
import com.hrs.demo.command.web.dto.request.CreateHotelRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
@Tag(name = "hotel-command-v1-controller")
public class HotelCommandController {

  private final IHotelService hotelService;

  @PostMapping
  public ResponseEntity<HotelDto> createHotel(@RequestBody CreateHotelRequest request) {
    return ResponseEntity.ok(hotelService.createHotel(request));
  }

}
