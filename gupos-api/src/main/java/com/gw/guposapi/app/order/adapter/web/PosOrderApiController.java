package com.gw.guposapi.app.order.adapter.web;

import com.gw.guposapi.app.order.adapter.web.request.CreateOrderRequest;
import com.gw.guposapi.app.order.adapter.web.response.OrderDto;
import com.gw.guposapi.app.order.application.in.OrderUseCase;
import com.gw.guposcore.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gw.guposapi.app.order.adapter.web.response.OrderDto.createList;

@RestController
@RequiredArgsConstructor
public class PosOrderApiController {
    private final OrderUseCase orderUseCase;

    @PostMapping("/api/v1/pos/order/create")
    public Order order(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderUseCase.createOrder(createOrderRequest);
    }

    @GetMapping("/api/v1/pos/order/list")
    public List<OrderDto> getOrderList() {
        List<Order> orderList = orderUseCase.getOrderList();
        return createList(orderList);
    }

    @PutMapping("/api/v1/pos/order/complete/{orderId}")
    public Order completeOrder(@PathVariable("orderId") Long orderId) {
        return orderUseCase.completeOrder(orderId);
    }

    @PutMapping("/api/v1/pos/order/cancel/{orderId}")
    public Order cancelOrder(@PathVariable("orderId") Long orderId) {
        return orderUseCase.cancelOrder(orderId);
    }

    @PutMapping("/api/v1/pos/order/cancel-complete/{orderId}")
    public Order cancelComepleteOrder(@PathVariable("orderId") Long orderId) {
        return orderUseCase.cancelCompletedOrder(orderId);
    }
}
