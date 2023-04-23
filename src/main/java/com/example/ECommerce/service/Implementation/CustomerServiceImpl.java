package com.example.ECommerce.service.Implementation;

import com.example.ECommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ECommerce.model.Cart;
import com.example.ECommerce.model.Customer;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
//    @Autowired
//    CustomerRepository customerRepository;
//    @Override
//    public String addCustomer(CustomerRequestDto customerRequestDto) {
//        Customer customer=new Customer();
//        customer.setName(customerRequestDto.getName());
//        customer.setAge(customerRequestDto.getAge());
//        customer.setEmaidId(customerRequestDto.getEmaidId());
//        customer.setMobNo(customerRequestDto.getMobNo());
//        customer.setAddress(customerRequestDto.getAddress());
//
//        Cart cart=new Cart();
//        cart.setTotalCost(0);
//        cart.setTotalCost(0);
//        cart.setCustomer(customer);
//
//        customer.setCart(cart);
//
//        customerRepository.save(customer);
//
//        return  "Customer added successfully";
//
//
//    }
}
