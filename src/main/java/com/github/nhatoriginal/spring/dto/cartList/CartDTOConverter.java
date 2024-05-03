package com.github.nhatoriginal.spring.dto.cartList;

import com.github.nhatoriginal.spring.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartDTOConverter {


    public CartItemDTO toCartItemDTO(Cart cart){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setImageUrl(cart.getMenuItemOption().getMenuItem().getImageUrl());
        cartItemDTO.setName(cart.getMenuItemOption().getMenuItem().getName());
        cartItemDTO.setPrice(cart.getMenuItemOption().getPrice());
        cartItemDTO.setQuantity(cart.getQuantity());
        return cartItemDTO;
    }
}
