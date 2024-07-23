package com.example.orders.consumer;


import com.example.orders.producer.DataSender;
import com.example.orders.service.OrderService;
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
  private final OrderService orderService;

  public KafkaConsumer(DataSender dataSender, OrderService orderService) {
    this.dataSender = dataSender;
    this.orderService = orderService;
  }


  @KafkaListener(
      topics = "${application.kafka.topic_cons}",
      groupId = "order_consumer")
  public void listen(@Payload Order order) {
    orderService.paidOrders(order);
    log.info("payed order:{}", order.getId());
  }
}

