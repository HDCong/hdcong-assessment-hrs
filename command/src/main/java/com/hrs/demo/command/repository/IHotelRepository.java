package com.hrs.demo.command.repository;

import com.hrs.demo.command.entities.Hotel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends
    JpaRepository<Hotel, Long> ,
    JpaSpecificationExecutor<Hotel> {
}