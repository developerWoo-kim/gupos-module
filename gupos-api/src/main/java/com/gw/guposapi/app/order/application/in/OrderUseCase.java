package com.gw.guposapi.app.order.application.in;

import com.gw.guposapi.app.order.adapter.web.request.CreateOrderRequest;
import com.gw.guposcore.domain.order.Order;

public interface OrderUseCase {

    Order createOrder(CreateOrderRequest createOrderRequest);
}
