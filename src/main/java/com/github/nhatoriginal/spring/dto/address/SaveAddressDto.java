package com.github.nhatoriginal.spring.dto.address;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveAddressDto {
  @NotNull
  private UUID userId;

  @NotBlank
  private String province;

  @NotBlank
  private String district;

  @NotBlank
  private String ward;

  @NotBlank
  private String detail;
}
