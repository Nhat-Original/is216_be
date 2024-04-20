package com.github.nhatoriginal.spring.repository;

import com.github.nhatoriginal.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  User findByEmail(String email);
}
