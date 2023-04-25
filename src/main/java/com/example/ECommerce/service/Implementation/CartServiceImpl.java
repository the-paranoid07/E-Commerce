package com.example.ECommerce.service.Implementation;

import com.example.ECommerce.DTO.ResponseDto.CartResponseDto;
import com.example.ECommerce.DTO.ResponseDto.ItemResponseDto;
import com.example.ECommerce.model.Cart;
import com.example.ECommerce.model.Customer;
import com.example.ECommerce.model.Item;
import com.example.ECommerce.repository.CartRepository;
import com.example.ECommerce.repository.CustomerRepository;
import com.example.ECommerce.service.CartService;
import com.example.ECommerce.transformer.CardTransformer;
import com.example.ECommerce.transformer.CartTransformer;
import com.example.ECommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Override
    public CartResponseDto addToCart(int customerId, Item item) {
        Customer customer=customerRepository.findById(customerId).get();
        Cart cart=customer.getCart();

        int newTotal=cart.getCartTotal()+item.getProduct().getPrice()* item.getRequiredQuantity();
        cart.setCartTotal(newTotal);
        //cart.getItems().add(item);

        int newNoOfItems=cart.getNumberOfItems()+item.getRequiredQuantity();
        cart.setNumberOfItems(newNoOfItems);

        Cart savedCart=cartRepository.save(cart);

        CartResponseDto cartResponseDto= CartTransformer.CartToCartResponseDto(savedCart);

        List<ItemResponseDto>itemResponseDtoList=new ArrayList<>();

        for(Item item1:cart.getItems()){
            ItemResponseDto itemResponseDto= ItemTransformer.ItemToItemResponseDto(item1);
            itemResponseDtoList.add(itemResponseDto);
        }

        cartResponseDto.setItems(itemResponseDtoList);

        return cartResponseDto;

    }
}
