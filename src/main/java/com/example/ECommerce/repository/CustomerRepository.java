package com.example.ECommerce.repository;

import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByEmailId(String emailId);

    @Query(value = "select * from customer c where c.age > :age",nativeQuery = true)
    List<Customer> getCustomersByAge(int age);
}
