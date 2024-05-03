package com.github.nhatoriginal.spring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
  @Column(name = "quantity", nullable = false)
  private int quantity;

  @Id
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Id
  @ManyToOne
  @JoinColumn(name = "menu_item_option_id", nullable = false)
  private MenuItemOption menuItemOption;
}
