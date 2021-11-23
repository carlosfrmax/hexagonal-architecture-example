package com.carlosfrmax.example.order.application.port.in;

import com.carlosfrmax.example.order.domain.Order;

public interface CreateOrderUseCase {

    Order createOrder(CreateOrderCommand command);

}
