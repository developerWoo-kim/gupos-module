package com.gw.guposcore.domain.order.repository;

import com.gw.guposcore.domain.order.Order;

import java.util.List;

public interface OrderCoreRepository {
    List<Order> findList();
}
