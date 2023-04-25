package com.example.ECommerce.repository;

import com.example.ECommerce.enums.CardType;
import com.example.ECommerce.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    List<Card> findByCardType(CardType cardType);
}
