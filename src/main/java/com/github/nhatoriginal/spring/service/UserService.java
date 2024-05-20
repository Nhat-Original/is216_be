package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.model.User;
import com.github.nhatoriginal.spring.repository.UserRepository;
import com.github.nhatoriginal.spring.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email không tồn tại");
    }
    return new CustomUserDetails(user);
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public User getUserById(String id) {
    return userRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));
  }
}
