package com.example.ECommerce.service.Implementation;

import com.example.ECommerce.DTO.RequestDto.ProductRequestDto;
import com.example.ECommerce.DTO.ResponseDto.ProductResponseDto;
import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.enums.ProductStatus;
import com.example.ECommerce.exception.InvalidProductException;
import com.example.ECommerce.exception.InvalidSellerException;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.model.Seller;
import com.example.ECommerce.repository.ProductRepository;
import com.example.ECommerce.repository.SellerRepository;
import com.example.ECommerce.service.ProductService;
import com.example.ECommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Override
    public List<ProductResponseDto> getProductsBySellerEmailId(String sellerEmailId) throws InvalidSellerException {

       try {
            Seller seller=sellerRepository.findByEmailId(sellerEmailId);
            List<Product>productList=seller.getProducts();
            List<ProductResponseDto>productResponseDtoList=new ArrayList<>();
            for(Product product:productList){
                ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);
                productResponseDtoList.add(productResponseDto);
            }
            return productResponseDtoList;
       }catch (Exception e){
           throw new InvalidSellerException("Invalid Seller EmailId");
       }

    }

    @Override
    public List<ProductResponseDto> getAvailableProducts() {
        List<Product>productList=productRepository.findByProductStatus(ProductStatus.AVAILABLE);
        List<ProductResponseDto>productResponseDtoList=new ArrayList<>();
        for(Product product:productList){
            ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }
    @Override
    public List<ProductResponseDto> getOutOfStockProducts() {
        List<Product>productList=productRepository.findByProductStatus(ProductStatus.OUT_OF_STOCK);
        List<ProductResponseDto>productResponseDtoList=new ArrayList<>();
        for(Product product:productList){
            ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }
        return productResponseDtoList;
    }

    @Override
    public ProductResponseDto deleteProduct(int sellerId, int productId) throws InvalidSellerException, InvalidProductException {
        Seller seller;
        try {
            seller=sellerRepository.findById(sellerId).get();
        }catch (Exception e){
            throw new InvalidSellerException("Invalid Seller Id");
        }
        if(!seller.getProducts().contains(productRepository.findById(productId).get())){
            throw new InvalidProductException("Product Id not valid");
        }
        ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(productRepository.findById(productId).get());
        seller.getProducts().remove(productRepository.findById(productId).get());
        productRepository.delete(productRepository.findById(productId).get());

        return productResponseDto;

    }

    @Override
    public List<ProductResponseDto> getProductsWithQuantityLessThan(int quantity) {
        List<Product>productList=productRepository.findAll();
        List<ProductResponseDto>productResponseDtoList=new ArrayList<>();

        for (Product product:productList){
            if(product.getQuantity() < quantity){
                ProductResponseDto productResponseDto=ProductTransformer.ProductToProductResponseDto(product);
                productResponseDtoList.add(productResponseDto);
            }
        }
        return productResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getProductsByPriceAndCategory(int price, String category) {

        List<Product>productList=productRepository.getProductsByPriceAndCategory(price,category);
        List<ProductResponseDto>productResponseDtoList=new ArrayList<>();

        for(Product product:productList){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }


    @Override
    public ProductResponseDto getCheapestProductInCategory(ProductCategory category) {
        List<ProductResponseDto>productResponseDtoList=getProductsByCategory(category);
        int cheapestPrice=Integer.MAX_VALUE;
        ProductResponseDto cheapestProduct=null;
        for (ProductResponseDto productResponseDto:productResponseDtoList){
            if(productResponseDto.getProductPrice() < cheapestPrice){
                cheapestPrice=productResponseDto.getProductPrice();
                cheapestProduct=productResponseDto;
            }
        }
        return cheapestProduct;
    }

    @Override
    public ProductResponseDto getCostliestProductInCategory(ProductCategory category) {
        List<ProductResponseDto>productResponseDtoList=getProductsByCategory(category);
        int costliestPrice=Integer.MIN_VALUE;
        ProductResponseDto costliestProduct=null;
        for (ProductResponseDto productResponseDto:productResponseDtoList){
            if(productResponseDto.getProductPrice() > costliestPrice){
                costliestPrice=productResponseDto.getProductPrice();
                costliestProduct=productResponseDto;
            }
        }
        return costliestProduct;
    }


}
