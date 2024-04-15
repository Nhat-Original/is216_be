package com.github.nhatoriginal.spring.repository;

import com.github.nhatoriginal.spring.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    User findByEmail(String email);
}
