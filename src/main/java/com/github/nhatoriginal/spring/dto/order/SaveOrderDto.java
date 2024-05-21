package com.github.nhatoriginal.spring.dto.order;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.github.nhatoriginal.spring.model.PaymentMethod;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveOrderDto {
  private String note;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate orderDate;

  @Enumerated(EnumType.STRING)
  private PaymentMethod paymentMethod;

  private long totalPrice;

  @NotNull
  private UUID userId;

  @NotNull
  private UUID addressId;

  @NotNull
  private UUID eateryId;

  @NotEmpty
  private List<OrderDetail> orderDetails;

  @Data
  public static class OrderDetail {
    @NotNull
    private UUID menuItemOptionId;

    private int quantity;
  }
}
