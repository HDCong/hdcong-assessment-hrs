package com.hrs.demo.command.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrs.demo.command.service.IMessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Profile("!test")
@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService implements IMessageProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @Override
  public void sendMessage(final String topic, final Object message) {
    try {
      String messageStr = objectMapper.writeValueAsString(message);
      kafkaTemplate.send(topic, messageStr);
      log.info("Sent to topic {}, message {}", topic, messageStr);
    } catch (Exception e) {
      log.error("Error when sending message", e);
    }
  }
}
