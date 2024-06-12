package com.hrs.demo.query.repository;

import com.hrs.demo.query.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends ElasticsearchRepository<Hotel, Long> {
  Page<Hotel> findByName(String hotelName, Pageable pageable);
}