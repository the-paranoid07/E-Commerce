package com.example.ECommerce.controller;

import com.example.ECommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ECommerce.DTO.ResponseDto.ItemResponseDto;
import com.example.ECommerce.model.Item;
import com.example.ECommerce.service.CartService;
import com.example.ECommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try {
            Item savedItem=itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto=cartService.addToCart(itemRequestDto.getCustomerId(),savedItem);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-items/{customerId}")
    public ResponseEntity getItems(@PathVariable("customerId")int customerId){
        try {
            List<ItemResponseDto> itemResponseDtoList = itemService.getItems(customerId);
            return new ResponseEntity(itemResponseDtoList, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
