package com.github.nhatoriginal.spring.controller;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.auth.AuthLoginDto;
import com.github.nhatoriginal.spring.dto.auth.AuthRegisterDto;
import com.github.nhatoriginal.spring.dto.auth.AuthResponseDto;
import com.github.nhatoriginal.spring.model.Role;
import com.github.nhatoriginal.spring.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@RestController
@RequestMapping(Endpoint.Auth.BASE)
public class AuthController {
  private final AuthService service;

  public AuthController(AuthService service) {
    this.service = service;
  }

  @PostMapping(Endpoint.Auth.REGISTER)
  public ResponseEntity<String> register(
      @RequestBody AuthRegisterDto request) {
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping(Endpoint.Auth.LOGIN)
  public ResponseEntity<AuthResponseDto> login(
      @RequestBody AuthLoginDto authLoginDto) {
    return ResponseEntity.ok(service.login(authLoginDto));
  }

  @GetMapping(Endpoint.Auth.HELLO)
  @PreAuthorize("hasRole('CUSTOMER')")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello");
  }
}
