package com.example.ECommerce.controller;

import com.example.ECommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ECommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/get-all-sellers")
    public ResponseEntity getAllSellers(){
        List<SellerResponseDto> sellerResponseDtos=sellerService.getAllSellers();
        return new ResponseEntity(sellerResponseDtos,HttpStatus.OK);
    }

    // get all sellers of a particular age
    @GetMapping("get-sellers-of-age/{age}")
    public ResponseEntity getSellersOfAge(@PathVariable("age")int age){
        List<SellerResponseDto> sellerResponseDtos=sellerService.getSellersOfAge(age);
        return new ResponseEntity(sellerResponseDtos,HttpStatus.OK);
    }

    // update seller info based on email id
    @PutMapping("/update-seller-using-emailId/{emailId}")
    public ResponseEntity updateSellerUsingEmailId(@PathVariable("emailId")String emailId,@RequestBody SellerRequestDto sellerRequestDto){
        try {
            SellerResponseDto sellerResponseDto=sellerService.updateSellerUsingEmailId(emailId,sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // delete a seller based on email
    @DeleteMapping("/delete-seller-by-emailId/{emailId}")
    public ResponseEntity deleteSellerByEmailId(@PathVariable("emailId") String emailId){
        try {
            SellerResponseDto sellerResponseDto=sellerService.deleteSellerByEmailId(emailId);
            return new ResponseEntity("Seller Deleted Successfully",HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //delete by id
    @DeleteMapping("/delete-seller-by-id/{id}")
    public ResponseEntity deleteSellerById(@PathVariable("id") int id){
        try {
            SellerResponseDto sellerResponseDto=sellerService.deleteSellerById(id);
            return new ResponseEntity("Seller Deleted Successfully",HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



}
