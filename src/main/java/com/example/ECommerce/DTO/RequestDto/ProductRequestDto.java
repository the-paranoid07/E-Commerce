package com.example.ECommerce.DTO.RequestDto;

import com.example.ECommerce.enums.ProductCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {
    int sellerId;
    String productName;
    String description;
    int quantity;
    int productPrice;
    ProductCategory productCategory;

}
