package com.example.shipping.consumer;


import com.example.shipping.producer.DataSender;
import org.example.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

  private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

  public final DataSender dataSender;

  public KafkaConsumer(DataSender dataSender) {
    this.dataSender = dataSender;
  }


  @KafkaListener(
      topics = "${application.kafka.topic_cons}",
      groupId = "shipping_consumer")
  public void listen(@Payload Order order) {
    log.info("Paid orders:{}", order.getId());
    dataSender.send(order);
  }
}
