package com.hrs.demo.command.repository;

import com.hrs.demo.command.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoomTypeRepository extends JpaRepository<RoomType, Long> {
}