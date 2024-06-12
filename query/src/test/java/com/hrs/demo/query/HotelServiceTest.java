package com.hrs.demo.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hrs.demo.query.entities.Hotel;
import com.hrs.demo.query.repository.IHotelRepository;
import com.hrs.demo.query.repository.IHotelRepositoryCustom;
import com.hrs.demo.query.service.implementation.HotelService;
import com.hrs.demo.query.web.dto.HotelDto;
import com.hrs.demo.query.web.dto.request.GetAllHotelCriteria;
import com.hrs.demo.query.web.exceptions.DataNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

class HotelServiceTest {

    @Mock
    private IHotelRepository hotelRepository;

    @Mock
    private IHotelRepositoryCustom hotelRepositoryCustom;

    @InjectMocks
    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_whenHotelsExist_shouldReturnHotelDtos() {
        GetAllHotelCriteria criteria = new GetAllHotelCriteria();
        Pageable pageable = PageRequest.of(0, 10);
        List<Hotel> hotels = Collections.singletonList(new Hotel());
        Page<Hotel> hotelPage = new PageImpl<>(hotels);

        when(hotelRepositoryCustom.search(any(GetAllHotelCriteria.class), any(Pageable.class))).thenReturn(hotelPage);

        Pair<Long, List<HotelDto>> result = hotelService.findAll(criteria, pageable);

        assertEquals(1, result.getLeft());
        assertEquals(1, result.getRight().size());
        verify(hotelRepositoryCustom, times(1)).search(criteria, pageable);
    }

    @Test
    void findAll_whenNoHotelsExist_shouldReturnEmptyList() {
        GetAllHotelCriteria criteria = new GetAllHotelCriteria();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Hotel> hotelPage = Page.empty();

        when(hotelRepositoryCustom.search(any(GetAllHotelCriteria.class), any(Pageable.class))).thenReturn(hotelPage);

        Pair<Long, List<HotelDto>> result = hotelService.findAll(criteria, pageable);

        assertEquals(0, result.getLeft());
        assertTrue(result.getRight().isEmpty());
        verify(hotelRepositoryCustom, times(1)).search(criteria, pageable);
    }

    @Test
    void findById_whenHotelExists_shouldReturnHotelDto() {
        Hotel hotel = new Hotel();
        hotel.setId(1L);

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        HotelDto result = hotelService.findById(1L);

        assertNotNull(result);
        verify(hotelRepository, times(1)).findById(1L);
    }

    @Test
    void findById_whenHotelDoesNotExist_shouldThrowDataNotFoundException() {
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> hotelService.findById(1L));
        verify(hotelRepository, times(1)).findById(1L);
    }
}
