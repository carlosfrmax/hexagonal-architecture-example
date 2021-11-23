package com.carlosfrmax.example.order.adapter.out.persistence;

import com.carlosfrmax.example.order.domain.Order;
import com.carlosfrmax.example.order.domain.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({OrderPersistenceAdapter.class, OrderMapper.class})
public class OrderPersistenceAdapterTest {

    @Autowired
    private OrderPersistenceAdapter adapterUnderTest;

    @Test
    void createOrder() {

        Set<OrderItem> orderItems = new HashSet<>();
        OrderItem orderItem1 = new OrderItem("1", 2L, new BigDecimal("10"));
        orderItems.add(orderItem1);

        Order order = new Order(ZonedDateTime.now(), "12345", orderItems);

        Order SavedOrder = adapterUnderTest.saveOrder(order);

        assertThat(SavedOrder.getOrderId()).isEqualTo(1L);
    }


}
