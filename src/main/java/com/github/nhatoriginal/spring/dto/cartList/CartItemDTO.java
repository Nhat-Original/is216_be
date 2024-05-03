package com.github.nhatoriginal.spring.dto.cartList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CartItemDTO {
  private String imageUrl;
  private String name;
  private double price;
  private int quantity;
}
