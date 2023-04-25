package com.example.ECommerce.DTO.ResponseDto;

import com.example.ECommerce.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {

    String cardNumber;
    String customerName;
    Date expiryDate;
    CardType cardType;
}
