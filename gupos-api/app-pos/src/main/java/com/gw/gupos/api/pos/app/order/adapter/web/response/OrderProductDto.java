package com.gw.gupos.api.pos.app.order.adapter.web.response;

import com.gw.guposcore.domain.order.OrderProduct;
import com.gw.guposcore.domain.order.OrderProductOption;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderProductDto {
    private Long orderProductId;
    private Long productId;
    private String productNm;
    private int productPrice;
    private int orderProductPrice;
    private int quantity;

    private List<OrderProductOptionDto> optionList = new ArrayList<>();

    public OrderProductDto(OrderProduct orderProduct) {
        this.orderProductId = orderProduct.getOrderProductId();

        if(Hibernate.isInitialized(orderProduct.getProduct()) && orderProduct.getProduct() != null) {
            this.productId = orderProduct.getProduct().getProductId();
            this.productNm = orderProduct.getProduct().getProductNm();
            this.productPrice = orderProduct.getProduct().getProductPrice();
        }

        if(Hibernate.isInitialized(orderProduct.getOrderProductOptionList()) && orderProduct.getOrderProductOptionList() != null) {
            for (OrderProductOption orderProductOption : orderProduct.getOrderProductOptionList()) {
                optionList.add(new OrderProductOptionDto(orderProductOption));
            }
        }

        this.orderProductPrice = orderProduct.getOrderProductPrice();
        this.quantity = orderProduct.getQuantity();
    }
}
