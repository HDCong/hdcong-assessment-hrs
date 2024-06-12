package com.hrs.demo.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import com.hrs.demo.command.entities.Hotel;
import com.hrs.demo.command.repository.IHotelRepository;
import com.hrs.demo.command.service.IMessageProducer;
import com.hrs.demo.command.service.implementation.HotelService;
import com.hrs.demo.command.web.dto.request.CreateHotelRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class HotelServiceTest {

  @InjectMocks
  private HotelService hotelService;

  @Mock
  private IHotelRepository hotelRepository;

  @Mock
  private IMessageProducer dummyProducerService;


  public HotelServiceTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenCreateHotel_thenReturnHotel() {
    Hotel hotel = new Hotel();
    hotel.setId(1L);
    hotel.setName("Hotel California");
    hotel.setDescription("A lovely place");

    final var hotelReq = new CreateHotelRequest();
    hotelReq.setName("Hotel California");
    hotelReq.setDescription("A lovely place");

    Mockito.when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

    final var result = hotelService.createHotel(hotelReq);

    assertNotNull(result);
    assertEquals(result.getId(), 1L);
    assertEquals(result.getName(), "Hotel California");
  }

}
