package com.example.ECommerce.service;

import com.example.ECommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ECommerce.exception.EmailIdAlreadyExistException;
import com.example.ECommerce.exception.InvalidSellerException;
import org.springframework.stereotype.Service;

@Service
public interface SellerService {


    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailIdAlreadyExistException;

    SellerResponseDto getSellerByEmailId(String emailId) throws InvalidSellerException;

    SellerResponseDto getSellerById(int id) throws InvalidSellerException;
}
