package com.github.nhatoriginal.spring.dto.cartList;

import java.util.UUID;

import com.github.nhatoriginal.spring.model.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
  private UUID menuItemOptionId;
  private String imageUrl;
  private String name;
  private Size size;
  private double price;
  private int quantity;
  private Eatery eatery;

  @Data
  @AllArgsConstructor
  public static class Eatery {
    private UUID id;
    private String name;
  }
}
