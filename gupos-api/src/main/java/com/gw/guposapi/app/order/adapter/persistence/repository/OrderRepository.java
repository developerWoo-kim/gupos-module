package com.gw.guposapi.app.order.adapter.persistence.repository;

import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.order.repository.OrderCoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderCoreRepository {
}
