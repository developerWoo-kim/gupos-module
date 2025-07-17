package com.gw.guposapi.app.order.adapter.persistence;

import com.gw.guposapi.app.order.adapter.persistence.repository.OrderRepository;
import com.gw.guposapi.app.order.application.out.OrderPort;
import com.gw.guposcore.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderPersistence implements OrderPort {
    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
