package com.example.ECommerce.service;

import com.example.ECommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.exception.InvalidProductException;
import com.example.ECommerce.exception.InvalidSellerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    List<ProductResponseDto> getProductsByCategory(ProductCategory productCategory);

    List<ProductResponseDto> getProductsBySellerEmailId(String sellerEmailId) throws InvalidSellerException;

    List<ProductResponseDto> getAvailableProducts();

    ProductResponseDto getCheapestProductInCategory(ProductCategory category);

    ProductResponseDto getCostliestProductInCategory(ProductCategory category);

    List<ProductResponseDto> getOutOfStockProducts();

    ProductResponseDto deleteProduct(int sellerId, int productId) throws InvalidSellerException, InvalidProductException;

    List<ProductResponseDto> getProductsWithQuantityLessThan(int quantity);
}
