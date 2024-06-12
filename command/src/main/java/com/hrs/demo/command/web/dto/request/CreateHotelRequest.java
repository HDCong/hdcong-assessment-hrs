package com.hrs.demo.command.web.dto.request;

import com.hrs.demo.command.web.dto.AddressDto;
import com.hrs.demo.command.web.dto.RoomDto;
import java.util.List;
import lombok.Data;

@Data
public class CreateHotelRequest {
    private String name;
    private String description;
    private List<RoomDto> rooms;
    private AddressDto address;
}
