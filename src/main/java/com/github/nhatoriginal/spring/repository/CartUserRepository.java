package com.github.nhatoriginal.spring.repository;

import com.github.nhatoriginal.spring.config.CartIdClassConfig;
import com.github.nhatoriginal.spring.model.Cart;
import com.github.nhatoriginal.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartUserRepository extends JpaRepository<Cart, CartIdClassConfig> {
  List<Cart> findByUser(User user);
}
