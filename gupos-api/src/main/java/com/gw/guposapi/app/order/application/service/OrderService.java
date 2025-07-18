package com.gw.guposapi.app.order.application.service;

import com.gw.guposapi.app.order.adapter.web.request.CreateOrderRequest;
import com.gw.guposapi.app.order.application.in.OrderUseCase;
import com.gw.guposapi.app.order.application.out.OrderPort;
import com.gw.guposapi.app.product.application.out.ProductPort;
import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.order.OrderProduct;
import com.gw.guposcore.domain.product.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.gw.guposapi.app.order.adapter.web.request.CreateOrderRequest.*;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {
    private final OrderPort orderPort;
    private final ProductPort productPort;

    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest) {

        Order createdOrder = Order.create(createOrderRequest.getOrderType());

        for (CreateOrderProductRequest orderProduct : createOrderRequest.getOrderProductList()) {
            Product foundProduct = productPort.findById(orderProduct.getProductId());
            OrderProduct createdOrderProduct = OrderProduct.create(foundProduct, orderProduct.getOrderProductPrice(), orderProduct.getQuantity());
            createdOrder.addOrderProduct(createdOrderProduct);
        }

        return orderPort.save(createdOrder);
    }

    @Override
    public List<Order> getOrderList() {
        return orderPort.findList();
    }
}
