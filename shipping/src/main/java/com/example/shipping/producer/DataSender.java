package com.example.shipping.producer;


import org.example.Order;

public interface DataSender {
  void send(Order order);
}
