package com.gw.gupos.api.pos.app.order.adapter.web.response;

import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.order.OrderProduct;
import com.gw.guposcore.domain.order.OrderStatus;
import com.gw.guposcore.domain.order.OrderType;
import lombok.Data;
import org.hibernate.Hibernate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId;
    private OrderStatus orderStatus;
    private OrderType orderType;
    private List<OrderProductDto> orderProductList = new ArrayList<>();
    private String orderTime;
    private String orderDateTime;

    public static List<OrderDto> createList(List<Order> orders) {
        List<OrderDto> list = new ArrayList<>();
        for (Order order : orders) {
            list.add(new OrderDto(order));
        }
        return list;
    }

    public OrderDto(Order order) {
        this.orderId = order.getOrderId();
        this.orderStatus = order.getOrderStatus();
        this.orderType = order.getOrderType();
        if (Hibernate.isInitialized(order.getOrderProductList()) && order.getOrderProductList() != null) {
            for (OrderProduct orderProduct : order.getOrderProductList()) {
                this.orderProductList.add(new OrderProductDto(orderProduct));
            }
        }
        this.orderTime = order.getOrderDateTime().format(DateTimeFormatter.ofPattern("hhmm"));
        this.orderDateTime = order.getOrderDateTime().format(DateTimeFormatter.ofPattern("MM.dd(E) hh:mm"));
    }
}
