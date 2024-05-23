package com.github.nhatoriginal.spring.controller;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.auth.AuthLoginDto;
import com.github.nhatoriginal.spring.dto.auth.AuthRegisterDto;
import com.github.nhatoriginal.spring.dto.auth.AuthResponseDto;
import com.github.nhatoriginal.spring.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(Endpoint.Auth.BASE)
public class AuthController {
  private final AuthService service;

  public AuthController(AuthService service) {
    this.service = service;
  }

  @PostMapping(Endpoint.Auth.REGISTER)
  public ResponseEntity<String> register(
      @Validated @RequestBody AuthRegisterDto request) {
    String newUser = service.register(request);
    URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/user/{id}")
            .buildAndExpand(newUser)
            .toUri();
    return ResponseEntity.created(location).body(newUser);
  }

  @PostMapping(Endpoint.Auth.LOGIN)
  public ResponseEntity<AuthResponseDto> login(
      @Validated @RequestBody AuthLoginDto authLoginDto) {
    return ResponseEntity.ok(service.login(authLoginDto));
  }
}
