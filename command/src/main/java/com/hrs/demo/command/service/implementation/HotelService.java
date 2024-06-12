package com.hrs.demo.command.service.implementation;

//import com.hrs.demo.command.entities.Room;
import com.hrs.demo.command.entities.RoomType;
import com.hrs.demo.command.utils.Constants;
import com.hrs.demo.command.entities.Hotel;
import com.hrs.demo.command.mapper.IHotelMapper;
import com.hrs.demo.command.repository.IHotelRepository;
import com.hrs.demo.command.service.IHotelService;
import com.hrs.demo.command.service.IMessageProducer;
import com.hrs.demo.command.web.dto.HotelDto;
import com.hrs.demo.command.web.dto.request.CreateHotelRequest;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService implements IHotelService {

  private final IHotelRepository hotelRepository;
  private final IMessageProducer messageService;

  @PostConstruct
  public void syncData() {
    // only for demo purpose, to sync init data to elastic search.
    hotelRepository.findAll()
        .forEach(hotel -> {
      messageService.sendMessage(Constants.HOTEL_TOPIC, hotel);
    });
  }
  @Override
  @Transactional
  public HotelDto createHotel(final CreateHotelRequest request) {
    final Hotel hotel = IHotelMapper.INSTANCE.toEntity(request);
    List<RoomType> rooms = CollectionUtils.emptyIfNull(request.getRooms())
        .stream()
        .map(IHotelMapper.INSTANCE::toRoomEntity)
        .peek((r) ->  r.setHotel(hotel))
        .toList();
    hotel.setRoomTypes(rooms);
    Hotel savedHotel = hotelRepository.save(hotel);
    messageService.sendMessage(Constants.HOTEL_TOPIC, savedHotel);
    return IHotelMapper.INSTANCE.toDto(savedHotel);
  }


}
