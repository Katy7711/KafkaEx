package com.example.payment.config;


import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;


@Configuration
public class Config {
  public final String topicName;
  @Value("${spring.kafka.producer.bootstrap-servers}")
  public String bootstrapServers;

  public Config(@Value("${application.kafka.topic_prod}") String topicName) {
    this.topicName = topicName;
  }
  @Bean
  public Map<String,Object> producerConfigs(){
    Map<String,Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return props;
  }


  @Bean
  public ProducerFactory<String, Order> producerFactory(){
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, Order> kafkaTemplate(){
    return new KafkaTemplate<>(producerFactory());

  }

  @Bean
  public NewTopic topic() {
    return TopicBuilder.name(topicName).partitions(4).replicas(4).build();
  }


}
