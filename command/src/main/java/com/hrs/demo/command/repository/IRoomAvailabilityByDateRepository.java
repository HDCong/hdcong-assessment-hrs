package com.hrs.demo.command.repository;

import com.hrs.demo.command.entities.RoomAvailabilityByDate;
import com.hrs.demo.command.entities.RoomType;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomAvailabilityByDateRepository extends JpaRepository<RoomAvailabilityByDate, Long> {
  Optional<RoomAvailabilityByDate> findByRoomTypeAndDate(RoomType roomType, LocalDate date);

}