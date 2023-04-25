package com.example.ECommerce.service;

import com.example.ECommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ECommerce.DTO.ResponseDto.ItemResponseDto;
import com.example.ECommerce.exception.InvalidCustomerException;
import com.example.ECommerce.exception.InvalidProductException;
import com.example.ECommerce.exception.ProductOutOfStockException;
import com.example.ECommerce.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    Item addItem(ItemRequestDto itemRequestDto) throws InvalidProductException, InvalidCustomerException, ProductOutOfStockException;

    List<ItemResponseDto> getItems(int customerId) throws InvalidCustomerException;
}
