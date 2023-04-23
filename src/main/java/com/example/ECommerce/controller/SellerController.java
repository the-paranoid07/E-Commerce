package com.example.ECommerce.controller;

import com.example.ECommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ECommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        try {
            SellerResponseDto sellerResponseDto=sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //  GET a seller by email
    @GetMapping("get-seller-by-emailId/{emailId}")
    public ResponseEntity getSellerByEmailId(@PathVariable("emailId") String emailId){
        try {
            SellerResponseDto sellerResponseDto=sellerService.getSellerByEmailId(emailId);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get by id
    @GetMapping("get-seller-by-id/{id}")
    public ResponseEntity getSellerById(@PathVariable("id") int id){
        try {
            SellerResponseDto sellerResponseDto=sellerService.getSellerById(id);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get all seller


    // update seller info based on email id

    // delete a seller based on email

    //delete by id

    // get all sellers of a particular age
}
