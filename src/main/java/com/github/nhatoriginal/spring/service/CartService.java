package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.dto.cartList.CartDTOConverter;
import com.github.nhatoriginal.spring.dto.cartList.CartItemDTO;
import com.github.nhatoriginal.spring.dto.cartList.SaveCartItemDto;
import com.github.nhatoriginal.spring.model.Cart;
import com.github.nhatoriginal.spring.model.MenuItemOption;
import com.github.nhatoriginal.spring.model.User;
import com.github.nhatoriginal.spring.repository.CartUserRepository;
import com.github.nhatoriginal.spring.repository.MenuItemOptionRepository;
import com.github.nhatoriginal.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CartService {
  @Autowired
  private CartUserRepository cartRepository;

  @Autowired
  private CartDTOConverter cartDTOConverter;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MenuItemOptionRepository menuItemOptionRepository;

  public List<CartItemDTO> getCartItemList(UUID id) {
    User user = userRepository.findById(id).get();
    List<Cart> cartList = cartRepository.findByUser(user);
    List<CartItemDTO> cartItemDTOS = new ArrayList<CartItemDTO>();
    for (Cart cart : cartList) {
      CartItemDTO cartItemDTO = cartDTOConverter.toCartItemDTO(cart);
      cartItemDTOS.add(cartItemDTO);
    }
    return cartItemDTOS;
  }

  public Cart save(SaveCartItemDto saveCartItemDto) {
    User user = userRepository.findById(saveCartItemDto.getUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    MenuItemOption menuItemOption = menuItemOptionRepository.findById(saveCartItemDto.getMenuItemOptionId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item option not found"));

    Cart cart = new Cart();
    cart.setUser(user);
    cart.setMenuItemOption(menuItemOption);
    cart.setQuantity(saveCartItemDto.getQuantity());

    return cartRepository.save(cart);
  }
}
