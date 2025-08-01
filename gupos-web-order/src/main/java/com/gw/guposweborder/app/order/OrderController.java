package com.gw.guposweborder.app.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController {

    @GetMapping("/order-detail")
    public String orderDetail() {
        return "app/order/order_detail";
    }
}
