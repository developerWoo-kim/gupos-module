package com.gw.guposapi.app.order.adapter.web;

import com.gw.guposcore.domain.order.OrderProduct;
import com.gw.guposcore.domain.order.OrderStatus;
import com.gw.guposcore.domain.order.OrderType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    @PostMapping("/api/v1/order")
    public String order() {
        return "success";
    }

}
