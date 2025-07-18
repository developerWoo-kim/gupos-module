package com.gw.guposapi.app.order.adapter.persistence;

import com.gw.guposapi.app.order.adapter.persistence.repository.OrderRepository;
import com.gw.guposapi.app.order.application.out.OrderPort;
import com.gw.guposcore.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderPersistence implements OrderPort {
    private final OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findList() {
        return orderRepository.findList();
    }
}
