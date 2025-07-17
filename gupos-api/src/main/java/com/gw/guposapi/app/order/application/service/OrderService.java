package com.gw.guposapi.app.order.application.service;

import com.gw.guposapi.app.order.adapter.web.request.CreateOrderRequest;
import com.gw.guposapi.app.order.application.in.OrderUseCase;
import com.gw.guposapi.app.order.application.out.OrderPort;
import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.order.OrderProduct;
import com.gw.guposcore.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.gw.guposapi.app.order.adapter.web.request.CreateOrderRequest.*;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {
    private final OrderPort orderPort;

    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest) {

        Order createdOrder = Order.create(createOrderRequest.getOrderStatus(), createOrderRequest.getOrderType());

        for (CreateOrderProductRequest orderProduct : createOrderRequest.getOrderProductList()) {

            OrderProduct createdOrderProduct = OrderProduct.create(new Product(), orderProduct.getOrderProductPrice(), orderProduct.getQuantity());

            createdOrder.addOrderProduct(createdOrderProduct);
        }


        return null;
    }
}
