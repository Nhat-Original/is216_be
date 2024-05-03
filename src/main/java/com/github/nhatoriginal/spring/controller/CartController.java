package com.github.nhatoriginal.spring.controller;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.cartList.CartItemDTO;
import com.github.nhatoriginal.spring.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
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

//     @DeleteMapping(Endpoint.Cart.DELETE)
//     public void deleteItemCart(@PathVariable UUID){
//
//     }
}


