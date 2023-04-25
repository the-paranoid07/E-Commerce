package com.example.ECommerce.exception;

import javax.sound.sampled.Port;

public class ProductOutOfStockException extends Exception{
    public ProductOutOfStockException(String message){
        super(message);
    }
}
