package com.example.orders.producer;


import org.example.Order;

public interface DataSender {
  void send(Order order);
}
