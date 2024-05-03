package com.github.nhatoriginal.spring.controller;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.Payment.PaymentDTO;
import com.github.nhatoriginal.spring.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping(Endpoint.Payment.BASE)
    public List<PaymentDTO> getPaymentFromCartList(@PathVariable UUID id) {
        return paymentService.getPaymentFromCartList(id);
    }
}
