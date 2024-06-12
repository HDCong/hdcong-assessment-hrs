package com.hrs.demo.query;

import com.hrs.demo.query.repository.IBookingRepository;
import com.hrs.demo.query.repository.IHotelRepository;
import com.hrs.demo.query.repository.IHotelRepositoryCustom;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootTest
class QueryApplicationTests {

    @MockBean
    private IHotelRepository hotelRepository;
    @MockBean
    private IBookingRepository bookingRepository;
    @MockBean
    private IHotelRepositoryCustom hotelRepositoryCustom;

    @MockBean
    private ElasticsearchOperations elasticsearchOperations;

    @Test
    void contextLoads() {
    }
}