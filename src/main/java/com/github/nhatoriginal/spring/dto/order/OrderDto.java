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
  public UUID id;
  public LocalDate orderDate;
  public long totalPrice;
  public String note;
  public PaymentMethod paymentMethod;
  public DeliveryStatus deliveryStatus;
}
