package com.example.orders.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.example.Order;
import org.example.Status;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  List<Order> orders = new ArrayList<>();


  public Order createOrder(Order createOrder) {
    Order order = new Order();
    order.setName(createOrder.getName());
    order.setPrice(createOrder.getPrice());
    order.setAmount(createOrder.getAmount());
    order.setStatus(Status.PAID);
    orders.add(order);
    return order;
  }

  public void paidOrders(Order order) {
    for (Order order1 : orders) {
      if (Objects.equals(order.getId(), order1.getId())) {
        order1.setStatus(Status.PAID);
      }
    }
  }
}

