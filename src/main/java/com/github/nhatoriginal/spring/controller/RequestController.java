package com.github.nhatoriginal.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
  @GetMapping("/hello")
  @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello");
  }
}
