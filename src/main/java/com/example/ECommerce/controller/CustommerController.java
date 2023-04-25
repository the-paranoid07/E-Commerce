package com.example.ECommerce.controller;

import com.example.ECommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.exception.EmailIdAlreadyExistException;
import com.example.ECommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustommerController {

    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        try {
            CustomerResponseDto customerResponseDto=customerService.addCustomer(customerRequestDto);
            return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    // view all customers
    @GetMapping("/get-all-customers")
    public ResponseEntity getAllCustomers(){

        List<CustomerResponseDto> customerResponseDtoList=customerService.getAllCustomers();

        return new ResponseEntity(customerResponseDtoList,HttpStatus.OK);
    }

    // get a customer by email/mob

    // get all customers whose age is greater than 25
    @GetMapping("/get/{age}")
    public ResponseEntity getCustomersByAge(@PathVariable("age")int age){
        List<CustomerResponseDto> customerResponseDtoList=customerService.getCustomersByAge(age);

        return new ResponseEntity(customerResponseDtoList,HttpStatus.OK);
    }

    // get all customers who use VISA card
    @GetMapping("/get-customers/{cardType}")
    public ResponseEntity getCustomersByCardType(@PathVariable("cardType") CardType cardType){
        List<CustomerResponseDto> customerResponseDtoList=customerService.getCustomersByCardType(cardType);

        return new ResponseEntity(customerResponseDtoList,HttpStatus.OK);
    }

    // update a customer info by email

    // delete a customer by email/mob

}
