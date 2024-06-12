package com.hrs.demo.query.repository;

import com.hrs.demo.query.entities.Booking;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookingRepository extends ElasticsearchRepository<Booking, Long> {
}