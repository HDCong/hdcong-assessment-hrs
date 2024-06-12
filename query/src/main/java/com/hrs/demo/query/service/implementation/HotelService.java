package com.hrs.demo.query.service.implementation;

import com.hrs.demo.query.entities.Hotel;
import com.hrs.demo.query.mapper.IHotelMapper;
import com.hrs.demo.query.repository.IHotelRepository;
import com.hrs.demo.query.repository.IHotelRepositoryCustom;
import com.hrs.demo.query.service.IHotelService;
import com.hrs.demo.query.web.dto.HotelDto;
import com.hrs.demo.query.web.dto.request.GetAllHotelCriteria;
import com.hrs.demo.query.web.exceptions.DataNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService implements IHotelService {

  private final IHotelRepository hotelRepository;
  private final IHotelRepositoryCustom hotelRepositoryCustom;

  @Override
  public Pair<Long, List<HotelDto>> findAll(GetAllHotelCriteria request, Pageable page) {
    final var result = hotelRepositoryCustom.search(request, page);
    if (result.isEmpty()) {
      return Pair.of(0L, new ArrayList<>());
    }
    return Pair.of(
        result.getTotalElements(),
        result.getContent()
            .stream()
            .map(IHotelMapper.INSTANCE::toDto)
            .collect(Collectors.toList())
    );
  }

  @Override
  public HotelDto findById(Long id) {
    return IHotelMapper.INSTANCE.toDto(
        hotelRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException(Hotel.class.getSimpleName(), id)));
  }
}
