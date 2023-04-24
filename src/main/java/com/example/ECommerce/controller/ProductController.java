package com.example.ECommerce.controller;

import com.example.ECommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.exception.InvalidSellerException;
import com.example.ECommerce.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    @GetMapping("/get-products-by-seller/{sellerEmailId}")
    public ResponseEntity getProductsBySellerEmailId(@PathVariable("sellerEmailId") String sellerEmailId){
        try {
            List<ProductResponseDto> productResponseDtoList = productService.getProductsBySellerEmailId(sellerEmailId);
            return new ResponseEntity(productResponseDtoList, HttpStatus.CREATED);
        }catch (Exception e){
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    }

    // delete a product by seller id and product id
    @DeleteMapping("/delete-a-product")
    public ResponseEntity deleteProduct(@RequestParam int sellerId, @RequestParam int productId){
        try {
            ProductResponseDto productResponseDto=productService.deleteProduct(sellerId,productId);
            return new ResponseEntity("Product deleted successfully",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // return top 5 cheapest products


    // return top 5 costliest products


    // return all out of stock products
    @GetMapping("/get-outOfStock-products")
    public ResponseEntity getOutOfStockProducts(){
        try {
            List<ProductResponseDto> productResponseDtoList = productService.getOutOfStockProducts();
            return new ResponseEntity(productResponseDtoList, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // return all available products
    @GetMapping("/get-available-products")
    public ResponseEntity getAvailableProducts(){
        try {
            List<ProductResponseDto> productResponseDtoList = productService.getAvailableProducts();
            return new ResponseEntity(productResponseDtoList, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // return all products that have quantity less than 10
    @GetMapping("/get-products-by-quantity-less-than")
    public ResponseEntity getProductsWithQuantityLessThan(@RequestParam int quantity){
        List<ProductResponseDto> productResponseDtoList=productService.getProductsWithQuantityLessThan(quantity);

        return new ResponseEntity(productResponseDtoList,HttpStatus.OK);
    }

    // return the cheapest product in a particular category
    @GetMapping("/get-cheapest-product-in-category/{category}")
    public ResponseEntity getCheapestProductInCategory(@PathVariable("category") ProductCategory category){
        ProductResponseDto productResponseDto=productService.getCheapestProductInCategory(category);
        return new ResponseEntity(productResponseDto,HttpStatus.OK);
    }

    // return the costliest product in a particular category
    @GetMapping("/get-costliest-product-in-category/{category}")
    public ResponseEntity getCostliestProductInCategory(@PathVariable("category") ProductCategory category){
        ProductResponseDto productResponseDto=productService.getCostliestProductInCategory(category);
        return new ResponseEntity(productResponseDto,HttpStatus.OK);
    }
}
