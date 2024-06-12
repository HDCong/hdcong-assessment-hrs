package com.hrs.demo.query.mapper;

import com.hrs.demo.query.entities.Booking;
import com.hrs.demo.query.entities.Hotel;
import com.hrs.demo.query.web.dto.BookingDto;
import com.hrs.demo.query.web.dto.HotelDto;
import org.mapstruct.Mapper;
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

}