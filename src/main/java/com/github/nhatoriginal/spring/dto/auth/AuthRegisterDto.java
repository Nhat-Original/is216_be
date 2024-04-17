package com.github.nhatoriginal.spring.dto.auth;

import com.github.nhatoriginal.spring.model.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
@Getter
public class AuthRegisterDto {
  @Email
  private String email;
  @NotEmpty
  private String password;
  private String fullName;

  @NotEmpty
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  private String phone;
}
