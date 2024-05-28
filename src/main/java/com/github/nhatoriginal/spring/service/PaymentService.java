package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.dto.Payment.PaymentDTO;
import com.github.nhatoriginal.spring.dto.Payment.PaymentDTOConverter;
import com.github.nhatoriginal.spring.model.Cart;
import com.github.nhatoriginal.spring.model.User;
import com.github.nhatoriginal.spring.repository.CartUserRepository;
import com.github.nhatoriginal.spring.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
  private final CartUserRepository cartRepository;


  private final PaymentDTOConverter paymentDTOConverter;


  private final UserRepository userRepository;
  public PaymentService(CartUserRepository cartRepository, PaymentDTOConverter paymentDTOConverter, UserRepository userRepository) {
    this.cartRepository = cartRepository;
    this.paymentDTOConverter = paymentDTOConverter;
    this.userRepository = userRepository;
  }

  public List<PaymentDTO> getPaymentFromCartList(UUID id) {
    User user = userRepository.findById(id).get();
    List<Cart> cartList = cartRepository.findByUser(user);
    List<PaymentDTO> paymentDTOList = new ArrayList<PaymentDTO>();
    for (Cart cart : cartList) {
      PaymentDTO paymentDTO = paymentDTOConverter.toPaymentDTO(cart);
      paymentDTOList.add(paymentDTO);
    }
    return paymentDTOList;
  }
}
