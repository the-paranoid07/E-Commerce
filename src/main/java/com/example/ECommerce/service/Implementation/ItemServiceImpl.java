package com.example.ECommerce.service.Implementation;

import com.example.ECommerce.DTO.RequestDto.CustomerRequestDto;
import com.example.ECommerce.DTO.RequestDto.ItemRequestDto;
import com.example.ECommerce.DTO.ResponseDto.ItemResponseDto;
import com.example.ECommerce.enums.ProductStatus;
import com.example.ECommerce.exception.InvalidCustomerException;
import com.example.ECommerce.exception.InvalidProductException;
import com.example.ECommerce.exception.ProductOutOfStockException;
import com.example.ECommerce.model.Customer;
import com.example.ECommerce.model.Item;
import com.example.ECommerce.model.Product;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.repository.ItemRepository;
import com.example.ECommerce.repository.ProductRepository;
import com.example.ECommerce.service.ItemService;
import com.example.ECommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws InvalidProductException, InvalidCustomerException, ProductOutOfStockException {
        Customer customer;
        try {
            customer=customerRepository.findById(itemRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new InvalidCustomerException("Invalid Customer Id!");
        }
        Product product;
        try {
            product=productRepository.findById(itemRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new InvalidProductException("Product does not exist!");
        }

        if(itemRequestDto.getRequiredQuantity() > product.getQuantity() ||
                product.getProductStatus() != ProductStatus.AVAILABLE){
            throw new ProductOutOfStockException("Product Out Of Stock!");
        }

        Item item=Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .cart(customer.getCart())
                .product(product)
                .build();

        product.getItemList().add(item);
       // Product savedProduct=productRepository.save(product);

        return itemRepository.save(item);
    }

    @Override
    public List<ItemResponseDto> getItems(int customerId) throws InvalidCustomerException {
        Customer customer;
        try {
            customer=customerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new InvalidCustomerException("Invalid Customer Id!");
        }
        List<Item>items=customer.getCart().getItems();
        List<ItemResponseDto>itemResponseDtoList=new ArrayList<>();
        for (Item item:items){
            ItemResponseDto itemResponseDto= ItemTransformer.ItemToItemResponseDto(item);
            itemResponseDtoList.add(itemResponseDto);
        }
        return itemResponseDtoList;
    }
}
