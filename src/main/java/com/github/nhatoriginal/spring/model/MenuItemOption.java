package com.github.nhatoriginal.spring.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "menu_item_option")
public class MenuItemOption {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(name = "size", nullable = false)
  private Size size;

  @Column(name = "price", nullable = false)
  private double price;

  @OneToMany(mappedBy = "menuItemOption")
  private List<OrderDetail> orderDetails;

  @OneToMany(mappedBy = "menuItemOption")
  private List<Cart> carts;

  @ManyToOne
  @JoinColumn(name = "menu_item_id", nullable = false)
  private MenuItem menuItem;
}
