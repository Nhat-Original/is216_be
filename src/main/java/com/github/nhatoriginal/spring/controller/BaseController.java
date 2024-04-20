package com.github.nhatoriginal.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.nhatoriginal.spring.constant.Endpoint;

@RestController
@RequestMapping
public class BaseController {
  @GetMapping(Endpoint.BASE)
  @PreAuthorize("hasAuthority(ROLE_CUSTOMER) || hasAuthority(ROLE_ADMIN) || hasAuthority(ROLE_OWNER)")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello");
  }
}
