package com.carlosfrmax.example.order.application.port.out;

public interface VerifyProductExistPort {
    boolean exist(String productId);
}
