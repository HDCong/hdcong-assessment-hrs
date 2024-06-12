package com.hrs.demo.command.mapper;

import com.hrs.demo.command.entities.Booking;
import com.hrs.demo.command.entities.RoomType;
import com.hrs.demo.command.entities.User;
import com.hrs.demo.command.web.dto.BookingDto;
import com.hrs.demo.command.web.dto.request.BookingRequest;
import java.math.BigDecimal;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
    componentModel = "spring",
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public interface IBookingMapper extends ICommonMapper<BookingDto, Booking> {

  IBookingMapper INSTANCE = Mappers.getMapper(IBookingMapper.class);

  @Mappings({
      @Mapping(target = "status", constant = "INIT"),
  })
  Booking toEntity(BookingRequest request);

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "user", source = "user"),
      @Mapping(target = "status", constant = "INIT")
  })
  Booking toEntity(
      BookingRequest request,
      User user,
      BigDecimal totalBasePrice,
      List<RoomType> roomTypes);
}
