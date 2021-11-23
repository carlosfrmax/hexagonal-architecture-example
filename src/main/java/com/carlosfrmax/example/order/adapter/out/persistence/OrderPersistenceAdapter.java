package com.carlosfrmax.example.order.adapter.out.persistence;

import com.carlosfrmax.example.common.PersistenceAdapter;
import com.carlosfrmax.example.order.application.port.out.SaveOrderPort;
import com.carlosfrmax.example.order.application.port.out.VerifyProductExistPort;
import com.carlosfrmax.example.order.domain.Order;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class OrderPersistenceAdapter implements SaveOrderPort, VerifyProductExistPort {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order saveOrder(Order order) {
        OrderJpaEntity savedOrder = orderRepository.save(orderMapper.mapToJpaEntity(order));
        return orderMapper.mapToDomainEntity(savedOrder);
    }

    @Override
    public boolean exist(String productId) {
        //TODO
        return true;
    }
}
