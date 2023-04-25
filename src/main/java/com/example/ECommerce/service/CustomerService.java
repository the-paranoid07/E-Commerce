package com.example.ECommerce.service;

import com.example.ECommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.exception.EmailIdAlreadyExistException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws EmailIdAlreadyExistException;

    List<CustomerResponseDto> getAllCustomers();

    List<CustomerResponseDto> getCustomersByAge(int age);

    List<CustomerResponseDto> getCustomersByCardType(CardType cardType);
}
