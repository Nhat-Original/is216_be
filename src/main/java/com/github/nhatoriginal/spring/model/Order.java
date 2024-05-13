package com.github.nhatoriginal.spring.model;

import java.time.LocalDate;
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
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "order_date", nullable = false)
  private LocalDate orderDate;

  @Column(name = "total_price", nullable = false)
  private long totalPrice;

  @Column(name = "note", nullable = true)
  private String note;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_method", nullable = false)
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  @Column(name = "delivery_status", nullable = false)
  private DeliveryStatus deliveryStatus;

  @OneToMany(mappedBy = "order")
  private List<OrderDetail> orderDetails;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "eatery_id", nullable = false)
  private Eatery eatery;

  @ManyToOne
  @JoinColumn(name = "address_id", nullable = false)
  private Address address;

  @ManyToOne
  @JoinColumn(name = "shipper_id", nullable = false)
  private Shipper shipper;
}
