package com.github.nhatoriginal.spring.dto.order;

import java.time.LocalDate;
import java.util.UUID;

import com.github.nhatoriginal.spring.model.DeliveryStatus;
import com.github.nhatoriginal.spring.model.PaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
  private UUID id;
  private LocalDate orderDate;
  private long totalPrice;
  private String note;
  private PaymentMethod paymentMethod;
  private DeliveryStatus deliveryStatus;
  private Eatery eatery;

  @Data
  @AllArgsConstructor
  public static class Eatery {
    private UUID id;
    private String name;
  }
}
