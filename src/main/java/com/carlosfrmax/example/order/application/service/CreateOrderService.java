package com.carlosfrmax.example.order.application.service;

import com.carlosfrmax.example.common.UseCase;
import com.carlosfrmax.example.order.application.port.in.CreateOrderCommand;
import com.carlosfrmax.example.order.application.port.in.CreateOrderUseCase;
import com.carlosfrmax.example.order.application.port.out.SaveOrderPort;
import com.carlosfrmax.example.order.application.port.out.VerifyProductExistPort;
import com.carlosfrmax.example.order.domain.Order;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@UseCase
public class CreateOrderService implements CreateOrderUseCase {

    private final VerifyProductExistPort verifyProductExistPort;
    private final SaveOrderPort saveOrderPort;


    @Override
    public Order createOrder(CreateOrderCommand command) {

        command.getOrderItems().stream().forEach(orderItem -> {
            if(!verifyProductExistPort.exist(orderItem.getProductId())){
                throw new InvalidProductException(orderItem.getProductId());
            }
        });

        Order order = new Order(ZonedDateTime.now(), command.getCustomerId(), command.getOrderItems());

        return saveOrderPort.saveOrder(order);
    }
}
