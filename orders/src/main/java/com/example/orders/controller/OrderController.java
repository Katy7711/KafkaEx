package com.example.orders.controller;


import com.example.orders.producer.DataSender;
import com.example.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.example.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class OrderController {

  private final OrderService orderService;
  private final DataSender dataSender;


  @PostMapping("/newOrder")
  public ResponseEntity<Order> createOrder(@RequestBody Order createOrder) {
    Order orderResponseEntity = orderService.createOrder(createOrder);
    dataSender.send(createOrder);
    return ResponseEntity.ok(orderResponseEntity);
  }
}
