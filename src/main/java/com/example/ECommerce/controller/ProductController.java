package com.example.ECommerce.controller;

import com.example.ECommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.exception.InvalidSellerException;
import com.example.ECommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){
        try {
            ProductResponseDto productResponseDto=productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{category}")
    public ResponseEntity getProductsByCategory(@PathVariable("category") ProductCategory productCategory){
        List<ProductResponseDto> productResponseDtoList=productService.getProductsByCategory(productCategory);

        return new ResponseEntity(productResponseDtoList,HttpStatus.CREATED);
    }



    // Get all product by seller email id

    // delete a product by seller id and product id

    // return top 5 cheapest products

    // return top 5 costliest products

    // return all out of stock products

    // return all available products

    // return all products that have quantity less than 10

    // return the cheapest product in a particular category

    // return the costliest product in a particular category
}
