package com.github.nhatoriginal.spring.dto.cartList;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateCartItemQuantityDto {
  @Max(10)
  @Min(1)
  private int quantity;
}
