package com.example.notifications.consumer;


import org.example.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
  private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);



  @KafkaListener(
      topics = "${application.kafka.topic_cons}",
      groupId = "notification_consumer")
  public void listen(@Payload Order order) {
    log.info("Goods shipped:{}", order);
  }
}
