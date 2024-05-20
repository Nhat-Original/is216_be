package com.github.nhatoriginal.spring.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "eateries")
public class Eatery {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone", nullable = false)
  private String phone;

  @Column(name = "is_alive", nullable = false)
  private Boolean isAlive;

  @OneToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  @OneToOne(mappedBy = "eatery")
  private Menu menu;

  @OneToMany(mappedBy = "eatery")
  private List<Order> orders;

  @ManyToOne
  @JoinColumn(name = "address_id", nullable = false)
  private Address address;
}
