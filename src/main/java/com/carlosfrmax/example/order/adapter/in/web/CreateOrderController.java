package com.carlosfrmax.example.order.adapter.in.web;

import com.carlosfrmax.example.common.WebAdapter;
import com.carlosfrmax.example.order.application.port.in.CreateOrderCommand;
import com.carlosfrmax.example.order.application.port.in.CreateOrderUseCase;
import com.carlosfrmax.example.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class CreateOrderController {

    private final CreateOrderUseCase createOrderUseCase;

    @PostMapping(path = "/orders/create/{customerId}")
    void createOrder(@PathVariable("customerId") String customerId, @RequestBody Set<OrderItem> orderItems){

        CreateOrderCommand command = new CreateOrderCommand(customerId, orderItems);

        createOrderUseCase.createOrder(command);
    }
}
