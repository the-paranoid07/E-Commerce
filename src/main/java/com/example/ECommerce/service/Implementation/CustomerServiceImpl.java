package com.example.ECommerce.service.Implementation;

import com.example.ECommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ECommerce.DTO.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.exception.EmailIdAlreadyExistException;
import com.example.ECommerce.model.Card;
import com.example.ECommerce.model.Cart;
import com.example.ECommerce.model.Customer;
import com.example.ECommerce.repository.CardRepository;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.service.CustomerService;
import com.example.ECommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

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

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer>customerList=customerRepository.findAll();
        List<CustomerResponseDto>customerResponseDtoList=new ArrayList<>();

        for (Customer customer:customerList){
            customerResponseDtoList.add(CustomerTransformer
                    .CustomerToCustomerResponseDto(customer));
        }
        return customerResponseDtoList;
    }

    @Override
    public List<CustomerResponseDto> getCustomersByAge(int age) {
        List<Customer>customerList=customerRepository.getCustomersByAge(age);
        List<CustomerResponseDto>customerResponseDtoList=new ArrayList<>();

        for (Customer customer:customerList){
            customerResponseDtoList.add(CustomerTransformer
                    .CustomerToCustomerResponseDto(customer));
        }
        return customerResponseDtoList;
    }

    @Override
    public List<CustomerResponseDto> getCustomersByCardType(CardType cardType) {
        List<Card>cardList=cardRepository.findByCardType(cardType);
        List<Customer>customerList=new ArrayList<>();
        for(Card card:cardList){
            if(!customerList.contains(card.getCustomer())){
                customerList.add(card.getCustomer());
            }
        }
        List<CustomerResponseDto>customerResponseDtoList=new ArrayList<>();

        for (Customer customer:customerList){
            customerResponseDtoList.add(CustomerTransformer
                    .CustomerToCustomerResponseDto(customer));
        }
        return customerResponseDtoList;

    }
}
