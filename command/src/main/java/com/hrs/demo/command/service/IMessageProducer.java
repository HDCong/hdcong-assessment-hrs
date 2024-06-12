package com.hrs.demo.command.service;

public interface IMessageProducer {

  /**
   * Send message to topic
   * @param topic given topic
   * @param message given message
   */
  void sendMessage(String topic, Object message);

}
