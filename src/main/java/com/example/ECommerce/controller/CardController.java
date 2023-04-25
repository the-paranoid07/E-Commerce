package com.example.ECommerce.controller;

import com.example.ECommerce.DTO.RequestDto.CardRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CardResponseDto;
import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try {
            CardResponseDto cardResponseDto=cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    // get all VISA cards OR get all cards by cardType
    @GetMapping("get/{cardType}")
    public ResponseEntity getCardsByCardType(@PathVariable("cardType") CardType cardType){

        List<CardResponseDto>cardResponseDtoList=cardService.getCardsByCardType(cardType);
        return new ResponseEntity(cardResponseDtoList,HttpStatus.OK);
    }

    // get all MASTERCARD cards whose expirt is greater than 1 Jan 2025

    // Return the CardType which has maximum number of that card
//    @GetMapping("/get-max/{cardType")
//    public ResponseEntity getCardTypeWithMaxCount(@PathVariable("cardType") CardType cardType){
//
//    }
}
