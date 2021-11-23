package com.carlosfrmax.example.order.adapter.out.persistence;

import com.carlosfrmax.example.order.domain.Order;
import com.carlosfrmax.example.order.domain.OrderItem;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class OrderMapper {

    OrderJpaEntity mapToJpaEntity(Order order) {

        Set<OrderItemJpaEntity> OrderItemsJpa = new HashSet<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            OrderItemJpaEntity orderItemJpaEntity = new OrderItemJpaEntity(orderItem.getProductId(), orderItem.getQuantity(), orderItem.getPrice());
            OrderItemsJpa.add(orderItemJpaEntity);
        }

        return new OrderJpaEntity(
                order.getOrderId(),
                order.getCreationDate(),
                order.getCustomerId(),
                OrderItemsJpa);
    }

    Order mapToDomainEntity(OrderJpaEntity orderJpaEntity) {

        Set<OrderItem> orderItems = new HashSet<>();
        for (OrderItemJpaEntity orderItemJpaEntity : orderJpaEntity.getOrderItems()) {
            OrderItem orderItem = new OrderItem(orderItemJpaEntity.getProductId(), orderItemJpaEntity.getQuantity(), orderItemJpaEntity.getPrice());
            orderItems.add(orderItem);
        }

        return new Order(orderJpaEntity.getOrderId(), orderJpaEntity.getCreationDate(), orderJpaEntity.getCustomerId(), orderItems);

    }
}
