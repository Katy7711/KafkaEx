package com.example.payment.producer;


import org.example.Order;

public interface DataSender {
  void send(Order order);
}
