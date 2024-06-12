package com.hrs.demo.query.web.controller;

import com.hrs.demo.query.service.IBookingService;
import com.hrs.demo.query.web.dto.BookingDto;
import com.hrs.demo.query.web.dto.GetAllDto;
import com.hrs.demo.query.web.dto.request.GetAllBookingCriteria;
import com.hrs.demo.query.web.dto.request.GetAllHotelCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Tag(name = "booking-query-v1-controller")
public class BookingQueryController {

  private final IBookingService bookingService;

  @GetMapping("/my/{userId}")
  public ResponseEntity<GetAllDto<BookingDto>> getHotels(
      @PathVariable(name = "userId") String userId,
      @ParameterObject GetAllBookingCriteria request,
      @PageableDefault(size = 20) Pageable page
  ) {
    request.setUserId(userId);
    final Pair<Long, List<BookingDto>> result = bookingService.findAll(request, page);
    return ResponseEntity.ok(
        GetAllDto.<BookingDto>builder()
            .total(result.getLeft())
            .content(result.getRight())
            .build()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookingDto> getHotel(@PathVariable Long id) {
    return ResponseEntity.ok(bookingService.findById(id));
  }

}
