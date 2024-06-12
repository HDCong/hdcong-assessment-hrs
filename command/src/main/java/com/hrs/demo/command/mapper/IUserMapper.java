package com.hrs.demo.command.mapper;

import com.hrs.demo.command.entities.Hotel;
import com.hrs.demo.command.entities.RoomType;
import com.hrs.demo.command.entities.User;
import com.hrs.demo.command.web.dto.HotelDto;
import com.hrs.demo.command.web.dto.RoomDto;
import com.hrs.demo.command.web.dto.UserDto;
import com.hrs.demo.command.web.dto.request.BookingRequest;
import com.hrs.demo.command.web.dto.request.CreateHotelRequest;
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
public interface IUserMapper extends ICommonMapper<UserDto, User> {
  IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

  User toEntity(BookingRequest request);
}
