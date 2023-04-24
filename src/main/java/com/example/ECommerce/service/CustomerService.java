package com.example.ECommerce.service;

import com.example.ECommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.exception.EmailIdAlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws EmailIdAlreadyExistException;
}
