package com.example.ECommerce.controller;

import com.example.ECommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ECommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustommerController {

//    @Autowired
//    CustomerService customerService;
//
//    @PostMapping("/add")
//    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
//        return customerService.addCustomer(customerRequestDto);
//    }

}
