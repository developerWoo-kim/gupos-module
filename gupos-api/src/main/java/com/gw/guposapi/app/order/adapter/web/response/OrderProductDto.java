package com.gw.guposapi.app.order.adapter.web.response;

import com.gw.guposcore.domain.order.OrderProduct;
import lombok.Data;
import org.hibernate.Hibernate;

@Data
public class OrderProductDto {
    private Long orderProductId;
    private Long productId;
    private String productNm;
    private int productPrice;
    private int orderProductPrice;
    private int quantity;

    public OrderProductDto(OrderProduct orderProduct) {
        this.orderProductId = orderProduct.getOrderProductId();

        if(Hibernate.isInitialized(orderProduct.getProduct()) && orderProduct.getProduct() != null) {
            this.productId = orderProduct.getProduct().getProductId();
            this.productNm = orderProduct.getProduct().getProductNm();
            this.productPrice = orderProduct.getProduct().getProductPrice();
        }

        this.orderProductPrice = orderProduct.getOrderProductPrice();
        this.quantity = orderProduct.getQuantity();
    }
}
