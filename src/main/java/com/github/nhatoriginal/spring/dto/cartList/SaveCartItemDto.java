package com.github.nhatoriginal.spring.dto.cartList;

import java.util.UUID;

import lombok.Data;

@Data
public class SaveCartItemDto {
  private UUID userId;
  private UUID menuItemOptionId;
  private int quantity;
}
