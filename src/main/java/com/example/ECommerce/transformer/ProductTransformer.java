package com.example.ECommerce.transformer;

import com.example.ECommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ECommerce.enums.ProductStatus;
import com.example.ECommerce.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformer {

    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getProductName())
                .description(productRequestDto.getDescription())
                .quantity(productRequestDto.getQuantity())
                .productCategory(productRequestDto.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }
    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .productStatus(product.getProductStatus())
                .quantity(product.getQuantity())
                .build();
    }
}
