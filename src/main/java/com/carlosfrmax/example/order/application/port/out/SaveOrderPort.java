package com.carlosfrmax.example.order.application.port.out;

import com.carlosfrmax.example.order.domain.Order;

public interface SaveOrderPort {
    Order saveOrder(Order order);
}
