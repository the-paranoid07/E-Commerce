package com.example.ECommerce.DTO.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {

    String productName;
    int quantity;
    int priceOfItem;
    int totalPrice;
}
