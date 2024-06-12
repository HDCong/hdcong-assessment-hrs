package com.hrs.demo.query.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrs.demo.query.entities.Booking;
import com.hrs.demo.query.entities.Hotel;
import com.hrs.demo.query.repository.IBookingRepository;
import com.hrs.demo.query.repository.IHotelRepository;
import com.hrs.demo.query.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("!test")
@RequiredArgsConstructor
public class KafkaConsumerService {

  private final IHotelRepository hotelRepository;
  private final IBookingRepository bookingRepository;
  private final ObjectMapper objectMapper;

  @KafkaListener(topics = Constants.HOTEL_TOPIC, groupId = Constants.GROUP_ID)
  public void consumeHotelMessage(String message) {
    try {
      log.info("Consumed message: {}", message);
      Hotel hotel = objectMapper.readValue(message, Hotel.class);
      hotelRepository.save(hotel);
    } catch (Exception e) {
      log.error("Error when consume message", e);
    }
  }

  @KafkaListener(topics = Constants.BOOKING_TOPIC, groupId = Constants.GROUP_ID)
  public void consumeBookingMessage(String message) {
    try {
      log.info("Consumed message: {}", message);
      Booking booking = objectMapper.readValue(message, Booking.class);
      bookingRepository.save(booking);
    } catch (Exception e) {
      log.error("Error when consume message", e);
    }
  }
}
