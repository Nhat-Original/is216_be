package com.github.nhatoriginal.spring.dto.address;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
  private UUID id;
  private String province;
  private String district;
  private String ward;
  private String detail;
}
