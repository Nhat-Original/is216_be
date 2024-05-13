package com.github.nhatoriginal.spring.repository;

import org.springframework.stereotype.Repository;
import com.github.nhatoriginal.spring.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
  List<Order> findByUserId(UUID userId);
}
