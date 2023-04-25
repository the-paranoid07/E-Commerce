package com.example.ECommerce.repository;

import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.enums.ProductStatus;
import com.example.ECommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);

    List<Product> findByProductStatus(ProductStatus available);

    //@Query(value = "SELECT p FROM Product p WHERE p.price > :price AND p.productCategory = :category",nativeQuery = false)
    @Query(value = "select * from product p where p.price > :price and product_category = :category",nativeQuery = true)
    List<Product> getProductsByPriceAndCategory(int price, String category);
}
