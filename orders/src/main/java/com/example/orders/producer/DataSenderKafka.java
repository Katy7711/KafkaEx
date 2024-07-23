package com.example.orders.producer;

import org.example.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataSenderKafka implements DataSender {
  private static final Logger log = LoggerFactory.getLogger(DataSenderKafka.class);

  private final KafkaTemplate<String,Order> template;

  @Value("${application.kafka.topic_prod}")
  private String topic;

  @Autowired
  public DataSenderKafka(KafkaTemplate<String,Order> template) {
    this.template = template;
  }


  @Override
  public void send(Order order) {
    try {
      log.info("order:{}", order);
      template.send(topic, order)
          .whenComplete(
              (result, ex) -> {
                if (ex == null) {
                  log.info(
                      "order id:{} was sent, offset:{} to topic {}",
                      order.getId(),
                      result.getRecordMetadata().offset(),
                      result.getRecordMetadata().topic());
                } else {
                  log.error("order id:{} was not sent", order.getId(), ex);
                }
              });
    } catch (Exception ex) {
      log.error("send error, order:{}", order, ex);
    }
  }
}
