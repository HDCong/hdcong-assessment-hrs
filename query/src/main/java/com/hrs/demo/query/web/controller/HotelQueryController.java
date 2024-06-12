package com.hrs.demo.query.web.controller;

import com.hrs.demo.query.service.IHotelService;
import com.hrs.demo.query.web.dto.GetAllDto;
import com.hrs.demo.query.web.dto.HotelDto;
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
@RequestMapping("/api/v1/hotels")
@RequiredArgsConstructor
@Tag(name = "hotel-query-v1-controller")
public class HotelQueryController {

  private final IHotelService hotelService;

  @GetMapping
  public ResponseEntity<GetAllDto<HotelDto>> getHotels(
      @ParameterObject GetAllHotelCriteria request,
      @PageableDefault(sort = {"id"}, direction = Direction.ASC, value = 20) Pageable page
  ) {
    final Pair<Long, List<HotelDto>> result = hotelService.findAll(request, page);
    return ResponseEntity.ok(
        GetAllDto.<HotelDto>builder()
            .total(result.getLeft())
            .content(result.getRight())
            .build()
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<HotelDto> getHotel(@PathVariable Long id) {
    return ResponseEntity.ok(hotelService.findById(id));
  }

}
