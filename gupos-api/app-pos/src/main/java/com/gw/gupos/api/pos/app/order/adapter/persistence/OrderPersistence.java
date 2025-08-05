package com.gw.gupos.api.pos.app.order.adapter.persistence;

import com.gw.gupos.api.pos.app.order.adapter.persistence.repository.OrderRepository;
import com.gw.gupos.api.pos.app.order.application.out.OrderPort;
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

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
