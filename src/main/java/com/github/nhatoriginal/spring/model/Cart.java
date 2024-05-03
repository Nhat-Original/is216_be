package com.github.nhatoriginal.spring.model;

import com.github.nhatoriginal.spring.config.CartIdClassConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "carts")
@IdClass(CartIdClassConfig.class)
public class Cart {
  @Id
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Id
  @ManyToOne
  @JoinColumn(name = "menu_item_option_id", nullable = false)
  private MenuItemOption menuItemOption;

  @Column(name = "quantity", nullable = false)
  private int quantity;


}
