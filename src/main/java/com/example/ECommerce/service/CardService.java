package com.example.ECommerce.service;

import com.example.ECommerce.DTO.RequestDto.CardRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CardResponseDto;
import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.exception.CardAlreadyExistException;
import com.example.ECommerce.exception.InvalidCustomerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException, CardAlreadyExistException;

    List<CardResponseDto> getCardsByCardType(CardType cardType);
}
