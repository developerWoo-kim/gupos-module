package com.gw.guposapi.app.order.application.out;

import com.gw.guposcore.domain.order.Order;

public interface OrderPort {
    Order save(Order order);
}
