package com.github.nhatoriginal.spring.repository;

import com.github.nhatoriginal.spring.model.Cart;
import com.github.nhatoriginal.spring.model.MenuItemOption;
import com.github.nhatoriginal.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartUserRepository extends JpaRepository<Cart, MenuItemOption> {
    List<Cart> findByUser(User user);
}
