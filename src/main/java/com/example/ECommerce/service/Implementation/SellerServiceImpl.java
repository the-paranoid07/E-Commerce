package com.example.ECommerce.service.Implementation;

import com.example.ECommerce.DTO.RequestDto.SellerRequestDto;
import com.example.ECommerce.DTO.ResponseDto.SellerResponseDto;
import com.example.ECommerce.exception.EmailIdAlreadyExistException;
import com.example.ECommerce.exception.InvalidSellerException;
import com.example.ECommerce.model.Seller;
import com.example.ECommerce.repository.SellerRepository;
import com.example.ECommerce.service.SellerService;
import com.example.ECommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailIdAlreadyExistException {

        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId()) != null){
            throw new EmailIdAlreadyExistException("Email Id already registered");
        }
        Seller seller=SellerTransformer.SellerRequestDtoTOSeller(sellerRequestDto);
        Seller savedSeller=sellerRepository.save(seller);

        SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;
    }

    @Override
    public SellerResponseDto getSellerByEmailId(String emailId) throws InvalidSellerException {
        Seller seller;
        try {
          seller=sellerRepository.findByEmailId(emailId);
      }catch (Exception e){
            throw new InvalidSellerException("Invalid Seller EmailId");
        }

        SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(seller);

        return sellerResponseDto;

    }

    @Override
    public SellerResponseDto getSellerById(int id) throws InvalidSellerException {
        Seller seller;
        try {
            seller=sellerRepository.findById(id).get();
        }catch (Exception e){
            throw new InvalidSellerException("Invalid Seller Id");
        }

        SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(seller);

        return sellerResponseDto;
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        List<Seller>sellerList=sellerRepository.findAll();
        List<SellerResponseDto>sellerResponseDtoList=new ArrayList<>();
        for(Seller seller:sellerList){
            SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(seller);
            sellerResponseDtoList.add(sellerResponseDto);
        }
        return sellerResponseDtoList;
    }

    @Override
    public SellerResponseDto deleteSellerByEmailId(String emailId) throws InvalidSellerException {

        if(sellerRepository.findByEmailId(emailId) == null){
            throw new InvalidSellerException("Invalid Seller EmailId");
        }
        SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(sellerRepository.findByEmailId(emailId));
        sellerRepository.delete(sellerRepository.findByEmailId(emailId));

        return sellerResponseDto;
    }

    @Override
    public SellerResponseDto deleteSellerById(int id) throws InvalidSellerException{
        if(sellerRepository.findById(id).get() == null){
            throw new InvalidSellerException("Invalid SellerId");
        }
        SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(sellerRepository.findById(id).get());
        sellerRepository.delete(sellerRepository.findById(id).get());

        return sellerResponseDto;
    }

    @Override
    public List<SellerResponseDto> getSellersOfAge(int age) {
        List<Seller>sellerList=sellerRepository.findByAge(age);
        List<SellerResponseDto>sellerResponseDtoList=new ArrayList<>();
        for(Seller seller:sellerList){
            SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(seller);
            sellerResponseDtoList.add(sellerResponseDto);
        }
        return sellerResponseDtoList;
    }

    @Override
    public SellerResponseDto updateSellerUsingEmailId(String emailId, SellerRequestDto sellerRequestDto) throws InvalidSellerException {
        Seller seller;
        try {
            seller=sellerRepository.findByEmailId(emailId);
        }catch (Exception e){
            throw new InvalidSellerException("Invalid Seller EmailId");
        }
        seller=SellerTransformer.SellerRequestDtoTOSeller(sellerRequestDto);
        Seller updatedSeller=sellerRepository.save(seller);

        SellerResponseDto sellerResponseDto=SellerTransformer.SellerToSellerResponseDto(seller);

        return sellerResponseDto;
    }
}
