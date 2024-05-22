package com.github.nhatoriginal.spring.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "menu_item_options")
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

  @OneToMany(mappedBy = "menuItemOption", fetch = FetchType.LAZY)
  private List<Cart> carts;
  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "menu_item_id", nullable = false)
  private MenuItem menuItem;
}
