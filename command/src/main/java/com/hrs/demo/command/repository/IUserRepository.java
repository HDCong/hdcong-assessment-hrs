package com.hrs.demo.command.repository;

import com.hrs.demo.command.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

  Optional<User> findByPhoneNumber(String phoneNumber);

}