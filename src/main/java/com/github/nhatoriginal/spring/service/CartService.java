package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.dto.cartList.CartDTOConverter;
import com.github.nhatoriginal.spring.dto.cartList.CartItemDTO;
import com.github.nhatoriginal.spring.model.Cart;
import com.github.nhatoriginal.spring.model.User;
import com.github.nhatoriginal.spring.repository.CartUserRepository;
import com.github.nhatoriginal.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
      @Autowired
    private CartUserRepository cartRepository;

      @Autowired
      private CartDTOConverter cartDTOConverter;

      @Autowired
      private UserRepository userRepository;
      public List<CartItemDTO> getCartItemList(UUID id){
          User user = userRepository.findById(id).get();
          List<Cart> cartList = cartRepository.findByUser(user);
          List<CartItemDTO> cartItemDTOS = new ArrayList<CartItemDTO>();
          for (Cart cart : cartList){
              CartItemDTO cartItemDTO = cartDTOConverter.toCartItemDTO(cart);
              cartItemDTOS.add(cartItemDTO);
          }
          return cartItemDTOS;
      }
}
