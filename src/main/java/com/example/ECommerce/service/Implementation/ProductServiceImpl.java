package com.example.ECommerce.service.Implementation;

import com.example.ECommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.exception.InvalidSellerException;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.model.Seller;
import com.example.ECommerce.repository.ProductRepository;
import com.example.ECommerce.repository.SellerRepository;
import com.example.ECommerce.service.ProductService;
import com.example.ECommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    SellerRepository sellerRepository;


    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller=sellerRepository.findById(productRequestDto.getSellerId()).get();
        }catch (Exception e){
            throw new InvalidSellerException("Invalid seller id");
        }
        Product product= ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);

        seller.getProducts().add(product);
        sellerRepository.save(seller);

        ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);

        return productResponseDto;

    }

    @Override
    public List<ProductResponseDto> getProductsByCategory(ProductCategory productCategory) {

        List<Product>productList=productRepository.findByProductCategory(productCategory);
        List<ProductResponseDto>productResponseDtoList=new ArrayList<>();
        for(Product product:productList){
            ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;

    }
}
