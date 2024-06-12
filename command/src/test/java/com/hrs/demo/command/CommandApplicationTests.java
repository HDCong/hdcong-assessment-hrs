package com.hrs.demo.command;

import com.hrs.demo.command.repository.IBookingRepository;
import com.hrs.demo.command.repository.IHotelRepository;
import com.hrs.demo.command.repository.IRoomTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class CommandApplicationTests {

    @MockBean
    private IHotelRepository hotelRepository;

    @MockBean
    private IBookingRepository bookingRepository;

		@MockBean
		private IRoomTypeRepository roomRepository;

    @Test
    void contextLoads() {
    }
}
