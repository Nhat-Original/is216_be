package com.github.nhatoriginal.spring.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.order.OrderDto;
import com.github.nhatoriginal.spring.service.OrderService;

@RestController
@RequestMapping(Endpoint.Order.BASE)
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping(Endpoint.Order.GET_BY_USER_ID)
  public List<OrderDto> findByUserId(@PathVariable UUID userId) {
    return orderService.findByUserId(userId);
  }
}
