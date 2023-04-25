package com.example.ECommerce.service;

import com.example.ECommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ECommerce.model.Item;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartResponseDto addToCart(int customerId,Item savedItem);
}
