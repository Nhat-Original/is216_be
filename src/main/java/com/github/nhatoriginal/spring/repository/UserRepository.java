package com.github.nhatoriginal.spring.repository;

import com.github.nhatoriginal.spring.model.Eatery;
import com.github.nhatoriginal.spring.model.User;
import com.google.googlejavaformat.Op;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByEmail(String email);


}
