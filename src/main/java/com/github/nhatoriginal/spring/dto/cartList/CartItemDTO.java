package com.github.nhatoriginal.spring.dto.cartList;

import java.util.UUID;

import com.github.nhatoriginal.spring.model.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDTO {
  private UUID menuItemOptionId;
  private UUID eateryId;
  private String imageUrl;
  private String name;
  private Size size;
  private double price;
  private int quantity;
}
