package com.example.ECommerce.transformer;

import com.example.ECommerce.DTO.ResponseDto.ItemResponseDto;
import com.example.ECommerce.model.Item;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemTransformer {
    public static ItemResponseDto ItemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .productName(item.getProduct().getName())
                .quantity(item.getRequiredQuantity())
                .priceOfItem(item.getProduct().getPrice())
                .totalPrice(item.getProduct().getPrice()*item.getRequiredQuantity())
                .build();
    }
}
