package com.gw.gupos.api.pos.app.order.adapter.web;

import com.gw.gupos.api.pos.app.order.adapter.web.request.CreateOrderRequest;
import com.gw.gupos.api.pos.app.order.application.in.OrderUseCase;
import com.gw.guposcore.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderUseCase orderUseCase;
    private final SimpMessagingTemplate template;

    @PostMapping("/api/v1/order")
    public Order order(@RequestBody CreateOrderRequest createOrderRequest) {
        Order order = orderUseCase.createOrder(createOrderRequest);
        template.convertAndSend("/sub/store/" + createOrderRequest.getStoreId(), order);
        return order;
    }

}
