package com.github.nhatoriginal.spring.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthLoginDto {
  @Email
  private String email;
  @NotBlank
  private String password;
}
