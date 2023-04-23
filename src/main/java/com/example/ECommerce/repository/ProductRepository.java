package com.example.ECommerce.repository;

import com.example.ECommerce.enums.ProductCategory;
import com.example.ECommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductCategory(ProductCategory productCategory);
}
