package com.github.nhatoriginal.spring.dto.cartList;

import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveCartItemDto {
  @NotNull
  private UUID userId;

  @NotNull
  private UUID menuItemOptionId;

  @Min(1)
  @NotNull
  private int quantity;
}
