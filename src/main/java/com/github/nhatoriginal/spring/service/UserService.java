package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.model.User;
import com.github.nhatoriginal.spring.repository.UserRepository;
import com.github.nhatoriginal.spring.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
            throw new UsernameNotFoundException(email);
        }
        return new CustomUserDetails(user);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User getUserById(String id) {
        return userRepository.findById(UUID.fromString(id)).orElse(null);
    }


}
