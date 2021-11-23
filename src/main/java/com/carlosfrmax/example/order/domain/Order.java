package com.carlosfrmax.example.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
public class Order {

    @Getter
    private long orderId;

    @Getter
    @NonNull
    private ZonedDateTime creationDate;

    @Getter
    @NonNull
    private String customerId;

    @Getter
    @NonNull
    private Set<OrderItem> orderItems;

}
