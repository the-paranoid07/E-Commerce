package com.example.ECommerce.transformer;

import com.example.ECommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ECommerce.model.Cart;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart){
        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .numberOfItem(cart.getNumberOfItems())
                .totalCost(cart.getCartTotal())
                .build();
    }
}
