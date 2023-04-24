package com.example.ECommerce.service.Implementation;

import com.example.ECommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ECommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.exception.EmailIdAlreadyExistException;
import com.example.ECommerce.model.Cart;
import com.example.ECommerce.model.Customer;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.service.CustomerService;
import com.example.ECommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws EmailIdAlreadyExistException {

        if(customerRepository.findByEmailId(customerRequestDto.getEmailId()) != null) {
            throw new EmailIdAlreadyExistException("Email already registered!");
        }
    Customer customer= CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart=Cart.builder()
                .cartTotal(0)
                .numberOfItems(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        Customer savedCustomer=customerRepository.save(customer);

        CustomerResponseDto customerResponseDto=CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

        return customerResponseDto;



    }
}
