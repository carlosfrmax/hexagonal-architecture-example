package com.carlosfrmax.example.order.application.port.in;

import com.carlosfrmax.example.order.domain.Order;
import com.carlosfrmax.example.order.domain.OrderItem;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Set;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateOrderCommand {

    @NotNull
    private String customerId;

    @NotNull
    private Set<OrderItem> orderItems;

    public CreateOrderCommand(String customerId, Set<OrderItem> orderItems) {
        this.customerId = customerId;
        this.orderItems = orderItems;
        //this.selfValidate();
    }
}
