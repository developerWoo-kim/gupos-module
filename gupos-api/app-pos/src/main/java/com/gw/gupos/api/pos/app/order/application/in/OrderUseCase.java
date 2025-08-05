package com.gw.gupos.api.pos.app.order.application.in;

import com.gw.gupos.api.pos.app.order.adapter.web.request.CreateOrderRequest;
import com.gw.guposcore.domain.order.Order;

import java.util.List;

public interface OrderUseCase {

    Order createOrder(CreateOrderRequest createOrderRequest);

    Order completeOrder(Long orderId);

    Order cancelOrder(Long orderId);

    Order cancelCompletedOrder(Long orderId);

    List<Order> getOrderList();
}
