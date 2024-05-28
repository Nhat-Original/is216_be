package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.config.CartIdClassConfig;
import com.github.nhatoriginal.spring.dto.cartList.CartDTOConverter;
import com.github.nhatoriginal.spring.dto.cartList.CartItemDTO;
import com.github.nhatoriginal.spring.dto.cartList.SaveCartItemDto;
import com.github.nhatoriginal.spring.dto.cartList.UpdateCartItemQuantityDto;
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

  private final CartUserRepository cartRepository;
  private final CartDTOConverter cartDTOConverter;
  private final UserRepository userRepository;
  private final MenuItemOptionRepository menuItemOptionRepository;
 public  CartService(CartUserRepository cartRepository, CartDTOConverter cartDTOConverter, UserRepository userRepository, MenuItemOptionRepository menuItemOptionRepository) {
    this.cartRepository = cartRepository;
    this.cartDTOConverter = cartDTOConverter;
    this.userRepository = userRepository;
    this.menuItemOptionRepository = menuItemOptionRepository;
  }
  public List<CartItemDTO> getCartItemList(UUID userId) {
    User user = userRepository.findById(userId).isPresent() ? userRepository.findById(userId).get() : null;
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
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

    MenuItemOption menuItemOption = menuItemOptionRepository.findById(saveCartItemDto.getMenuItemOptionId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tùy chọn món ăn không tồn tại"));

    CartIdClassConfig id = new CartIdClassConfig();
    id.setUser(user);
    id.setMenuItemOption(menuItemOption);
    if ((cartRepository.findById(id)).isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tùy chọn món ăn đã tồn tại trong giỏ hàng");
    }

    Cart cart = new Cart();
    cart.setUser(user);
    cart.setMenuItemOption(menuItemOption);
    cart.setQuantity(saveCartItemDto.getQuantity());

    return cartRepository.save(cart);
  }

  public void delete(UUID userId, UUID menuItemOptionId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

    MenuItemOption menuItemOption = menuItemOptionRepository.findById(menuItemOptionId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tùy chọn món ăn không tồn tại"));

    CartIdClassConfig id = new CartIdClassConfig();
    id.setUser(user);
    id.setMenuItemOption(menuItemOption);

    cartRepository.deleteById(id);
  }

  public void updateQuantity(UUID userId, UUID menuItemOptionId, UpdateCartItemQuantityDto body) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

    MenuItemOption menuItemOption = menuItemOptionRepository.findById(menuItemOptionId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tùy chọn món ăn không tồn tại"));

    CartIdClassConfig id = new CartIdClassConfig();
    id.setUser(user);
    id.setMenuItemOption(menuItemOption);

    Cart cart = cartRepository.findById(id)
        .orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tùy chọn món ăn Không tồn tại trong giỏ hàng"));

    cart.setQuantity(body.getQuantity());
    cartRepository.save(cart);
  }
}
