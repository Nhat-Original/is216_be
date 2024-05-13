package com.github.nhatoriginal.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.nhatoriginal.spring.dto.order.OrderDto;
import com.github.nhatoriginal.spring.repository.OrderRepository;
import com.github.nhatoriginal.spring.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private UserRepository userRepository;

  public List<OrderDto> findByUserId(UUID userId) {
    userRepository.findById(userId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return orderRepository.findByUserId(userId).stream().map(
        order -> new OrderDto(order.getId(), order.getOrderDate(), order.getTotalPrice(), order.getNote(),
            order.getPaymentMethod(), order.getDeliveryStatus()))
        .toList();
  }
}
