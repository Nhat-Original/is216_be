package com.github.nhatoriginal.spring.dto;

import com.github.nhatoriginal.spring.model.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@Setter
@Getter
public class AuthRegisterDto {
  @Email
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String fullName;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfBirth;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @NotBlank
  private String phone;
}
