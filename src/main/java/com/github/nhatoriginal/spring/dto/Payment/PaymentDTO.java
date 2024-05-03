package com.github.nhatoriginal.spring.dto.Payment;

import com.github.nhatoriginal.spring.model.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PaymentDTO {
    private String name;
    private int quantity;
    private Size size;
}
