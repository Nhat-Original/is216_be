package com.github.nhatoriginal.spring.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "province", nullable = false)
  private String province;

  @Column(name = "district", nullable = false)
  private String district;

  @Column(name = "ward", nullable = false)
  private String ward;

  @Column(name = "detail", nullable = false)
  private String detail;

  @OneToMany(mappedBy = "address")
  private List<Shipper> shippers;

  @OneToMany(mappedBy = "address")
  private List<Order> orders;

  @OneToMany(mappedBy = "address")
  private List<Eatery> eateries;

  @ManyToMany(mappedBy = "addresses")
  private List<User> users;
}
