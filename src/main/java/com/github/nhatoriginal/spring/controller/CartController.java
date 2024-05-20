package com.github.nhatoriginal.spring.controller;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.cartList.CartItemDTO;
import com.github.nhatoriginal.spring.dto.cartList.SaveCartItemDto;
import com.github.nhatoriginal.spring.dto.cartList.UpdateCartItemQuantityDto;
import com.github.nhatoriginal.spring.model.Cart;
import com.github.nhatoriginal.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(Endpoint.Cart.BASE)
public class CartController {
  @Autowired
  public CartService cartService;

  @GetMapping(Endpoint.Cart.GET_BY_USER_ID)
  public List<CartItemDTO> getCartItemList(@PathVariable UUID userId) {
    return cartService.getCartItemList(userId);
  }

  @PostMapping(Endpoint.Cart.CREATE)
  public ResponseEntity<String> save(@Validated @RequestBody SaveCartItemDto saveCartItemDto) {
    Cart cartItem = cartService.save(saveCartItemDto);

    if (cartItem == null) {
      return new ResponseEntity<>("Failed to save cart item", HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<>("Saved cart item successfully", HttpStatus.CREATED);
  }

  @DeleteMapping(Endpoint.Cart.DELETE)
  public ResponseEntity<String> delete(@PathVariable("userId") UUID userId,
      @PathVariable("menuItemOptionId") UUID menuItemOptionId) {
    cartService.delete(userId, menuItemOptionId);
    return new ResponseEntity<>("Deleted cart item successfully", HttpStatus.OK);
  }

  @PatchMapping(Endpoint.Cart.UPDATE_QUANTITY)
  public ResponseEntity<String> updateQuantity(@PathVariable("userId") UUID userId,
      @PathVariable("menuItemOptionId") UUID menuItemOptionId, @RequestBody UpdateCartItemQuantityDto body) {
    cartService.updateQuantity(userId, menuItemOptionId, body);
    return new ResponseEntity<>("Updated cart item quantity successfully", HttpStatus.OK);
  }
}
