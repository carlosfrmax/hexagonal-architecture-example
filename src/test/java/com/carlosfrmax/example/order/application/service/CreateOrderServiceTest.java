package com.carlosfrmax.example.order.application.service;

import com.carlosfrmax.example.order.application.port.in.CreateOrderCommand;
import com.carlosfrmax.example.order.application.port.out.SaveOrderPort;
import com.carlosfrmax.example.order.application.port.out.VerifyProductExistPort;
import com.carlosfrmax.example.order.application.service.CreateOrderService;
import com.carlosfrmax.example.order.application.service.InvalidProductException;
import com.carlosfrmax.example.order.domain.Order;
import com.carlosfrmax.example.order.domain.OrderItem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class CreateOrderServiceTest {

    private final VerifyProductExistPort verifyProductExistPort = Mockito.mock(VerifyProductExistPort.class);
    private final SaveOrderPort saveOrderPort = Mockito.mock(SaveOrderPort.class);
    private final CreateOrderService createOrderService = new CreateOrderService(verifyProductExistPort, saveOrderPort);

    @Test
    void givenOrderItemsDontExist_thenTheOrderIsNotCreated(){

        String customerId = "12345";
        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem1 = new OrderItem("1", 2L, new BigDecimal("10"));
        orderItems.add(orderItem1);
        OrderItem orderItem2 = new OrderItem("2", 2L, new BigDecimal("10"));
        orderItems.add(orderItem2);

        CreateOrderCommand command = new CreateOrderCommand(customerId, orderItems);

        given(verifyProductExistPort.exist("1"))
                .willReturn(true);
        given(verifyProductExistPort.exist("2"))
                .willReturn(false);

        InvalidProductException ex = assertThrows(InvalidProductException.class,
                () -> createOrderService.createOrder(command));
    }

    @Test
    void givenValidOrderItems_thenTheOrderIsCreated(){

        String customerId = "12345";
        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem1 = new OrderItem("1", 2L, new BigDecimal("10"));
        orderItems.add(orderItem1);
        OrderItem orderItem2 = new OrderItem("2", 2L, new BigDecimal("10"));
        orderItems.add(orderItem2);

        CreateOrderCommand command = new CreateOrderCommand(customerId, orderItems);

        given(verifyProductExistPort.exist("1"))
                .willReturn(true);
        given(verifyProductExistPort.exist("2"))
                .willReturn(true);

        ZonedDateTime now = ZonedDateTime.now();

        given(saveOrderPort.saveOrder(any(Order.class)))
                .willReturn(new Order(now, "12345", orderItems));

        Order savedOrder = createOrderService.createOrder(command);

        assertThat(savedOrder.getCreationDate()).isEqualTo(now);
    }
}
