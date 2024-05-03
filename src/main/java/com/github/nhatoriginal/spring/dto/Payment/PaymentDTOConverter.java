package com.github.nhatoriginal.spring.dto.Payment;


import com.github.nhatoriginal.spring.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class PaymentDTOConverter {

    public PaymentDTO toPaymentDTO(Cart cart){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setSize(cart.getMenuItemOption().getSize());
        paymentDTO.setName(cart.getMenuItemOption().getMenuItem().getName());
        paymentDTO.setQuantity(cart.getQuantity());
        return paymentDTO;
    }
}
