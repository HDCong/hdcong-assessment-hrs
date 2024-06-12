package com.hrs.demo.command.service.implementation.dummy;

import com.hrs.demo.command.service.IMessageProducer;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class DummyProducerService implements IMessageProducer {

  @Override
  public void sendMessage(String topic, Object message) {
    // do nothing
  }
}
