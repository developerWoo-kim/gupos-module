package com.gw.guposapi.app.order.application.service;

import com.gw.guposapi.app.order.adapter.web.request.CreateOrderRequest;
import com.gw.guposapi.app.order.application.in.OrderUseCase;
import com.gw.guposapi.app.order.application.out.OrderPort;
import com.gw.guposapi.app.product.application.in.ProductOptionUseCase;
import com.gw.guposapi.app.product.application.in.ProductUseCase;
import com.gw.guposapi.app.product.application.out.ProductPort;
import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.order.OrderProduct;
import com.gw.guposcore.domain.order.OrderProductOption;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductOption;
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

    private final ProductUseCase productUseCase;
    private final ProductOptionUseCase productOptionUseCase;

    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest) {

        Order createdOrder = Order.create(createOrderRequest.getOrderType());

        for (CreateOrderProductRequest orderProduct : createOrderRequest.getOrderProductList()) {
            Product foundProduct = productUseCase.getProductById(orderProduct.getProductId());

            OrderProduct createdOrderProduct = OrderProduct.create(foundProduct, orderProduct.getOrderProductPrice(), orderProduct.getQuantity());

            if(orderProduct.getOptionList() != null) {
                for (CreateOrderProductOptionRequest option : orderProduct.getOptionList()) {
                    ProductOption foundOption = productOptionUseCase.getOptionById(option.getOptionId());

                    OrderProductOption createdOrderProductOption = OrderProductOption.create(foundOption, option.getQuantity());
                    createdOrderProduct.addOption(createdOrderProductOption);
                }
            }

            createdOrder.addOrderProduct(createdOrderProduct);
        }

        return orderPort.save(createdOrder);
    }

    @Override
    public Order completeOrder(Long orderId) {
        Order foundOrder = orderPort.findById(orderId);
        foundOrder.complete();
        return foundOrder;
    }

    @Override
    public Order cancelOrder(Long orderId) {
        Order foundOrder = orderPort.findById(orderId);
        foundOrder.cancel();
        return foundOrder;
    }

    @Override
    public Order cancelCompletedOrder(Long orderId) {
        Order foundOrder = orderPort.findById(orderId);
        foundOrder.cancelCompleted();
        return foundOrder;
    }

    @Override
    public List<Order> getOrderList() {
        List<Order> list = orderPort.findList();
        for (Order order : list) {
            for (OrderProduct orderProduct : order.getOrderProductList()) {
                for (OrderProductOption orderProductOption : orderProduct.getOrderProductOptionList()) {
                    Long optionId = orderProductOption.getOptionId();
                }
            }
        }
        return list;
    }
}
