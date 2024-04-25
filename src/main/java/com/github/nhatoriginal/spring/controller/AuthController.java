package com.github.nhatoriginal.spring.controller;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.AuthLoginDto;
import com.github.nhatoriginal.spring.dto.AuthRegisterDto;
import com.github.nhatoriginal.spring.dto.AuthResponseDto;
import com.github.nhatoriginal.spring.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.Auth.BASE)
public class AuthController {
  private final AuthService service;

  public AuthController(AuthService service) {
    this.service = service;
  }



    @PostMapping(Endpoint.Auth.REGISTER)
    public ResponseEntity<String> register(
           @Validated  @RequestBody AuthRegisterDto request
            ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping(Endpoint.Auth.LOGIN)
    public ResponseEntity<AuthResponseDto> login(
           @Validated  @RequestBody AuthLoginDto authLoginDto
    ) {
        return ResponseEntity.ok(service.login(authLoginDto));
    }
}
