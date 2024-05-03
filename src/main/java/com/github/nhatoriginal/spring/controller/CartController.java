package com.github.nhatoriginal.spring.controller;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.cartList.CartItemDTO;
import com.github.nhatoriginal.spring.dto.cartList.SaveCartItemDto;
import com.github.nhatoriginal.spring.model.Cart;
import com.github.nhatoriginal.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(Endpoint.Cart.BASE)
public class CartController {
  @Autowired
  public CartService cartService;

  @GetMapping(Endpoint.Cart.GET_ALL)
  public List<CartItemDTO> getCartItemList(@PathVariable UUID id) {
    return cartService.getCartItemList(id);
  }

  @PostMapping(Endpoint.Cart.CREATE)
  public ResponseEntity<Cart> save(@RequestBody SaveCartItemDto saveCartItemDto) {
    Cart cart = cartService.save(saveCartItemDto);

    return new ResponseEntity<>(cart, HttpStatus.CREATED);
  }

  // @DeleteMapping(Endpoint.Cart.DELETE)
  // public void deleteItemCart(@PathVariable UUID){
  //
  // }
}
