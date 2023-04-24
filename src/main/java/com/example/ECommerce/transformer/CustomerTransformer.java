package com.example.ECommerce.transformer;

import com.example.ECommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .address(customerRequestDto.getAddress())
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .build();
    }
    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .address(customer.getAddress())
                .age(customer.getAge())
                .name(customer.getName())
                .mobNo(customer.getMobNo())
                .emailId(customer.getEmailId())
                .build();
    }
}
