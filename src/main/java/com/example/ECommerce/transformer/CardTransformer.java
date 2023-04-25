package com.example.ECommerce.transformer;

import com.example.ECommerce.DTO.RequestDto.CardRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CardResponseDto;
import com.example.ECommerce.model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardTransformer {

    public static Card CardRequestDtoTOCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNumber(cardRequestDto.getCardNumber())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .expiryDate(cardRequestDto.getExpiryDate())
                .build();
    }
    public static CardResponseDto CardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .expiryDate(card.getExpiryDate())
                .build();
    }
}
