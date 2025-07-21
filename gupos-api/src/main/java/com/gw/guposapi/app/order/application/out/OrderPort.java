package com.gw.guposapi.app.order.application.out;

import com.gw.guposcore.domain.order.Order;

import java.util.List;

public interface OrderPort {
    Order save(Order order);
    List<Order> findList();
    Order findById(Long orderId);
}
