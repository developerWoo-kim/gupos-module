package com.gw.guposapi.app.order.adapter.web.request;

import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.order.OrderStatus;
import com.gw.guposcore.domain.order.OrderType;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private String storeId;
    private OrderType orderType;
    private List<CreateOrderProductRequest> orderProductList;

    @Data
    public static class CreateOrderProductRequest {
        private Long productId;
        private int orderProductPrice;
        private int quantity;
    }
}
