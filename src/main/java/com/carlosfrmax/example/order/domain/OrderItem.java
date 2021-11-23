package com.carlosfrmax.example.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class OrderItem {

    @Getter
    private String productId;

    @Getter
    private Long quantity;

    @Getter
    private BigDecimal price;

}
