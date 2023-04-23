package com.example.ECommerce.transformer;

import com.example.ECommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ECommerce.model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
            return SellerResponseDto.builder()
                    .age(seller.getAge())
                    .name(seller.getName())
                    .build();
    }
    public static Seller SellerRequestDtoTOSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .age(sellerRequestDto.getAge())
                .mobNo(sellerRequestDto.getMobNo())
                .emailId(sellerRequestDto.getEmailId())
                .name(sellerRequestDto.getName())
                .build();
    }
}
