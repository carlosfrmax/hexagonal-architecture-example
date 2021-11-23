package com.carlosfrmax.example.order.application.service;

public class InvalidProductException extends RuntimeException{
    public InvalidProductException(String productId) {
        super(String.format("The product with id: %s is not valid", productId));
    }
}
