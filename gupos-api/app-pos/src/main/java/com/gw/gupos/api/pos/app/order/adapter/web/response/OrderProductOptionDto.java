package com.gw.gupos.api.pos.app.order.adapter.web.response;

import com.gw.guposcore.domain.order.OrderProductOption;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderProductOptionDto {
    private Long orderProductOptionId;
    private Long optionId;
    private String optionNm;
    private int optionPrice;
    private int quantity;

    public OrderProductOptionDto(OrderProductOption orderProductOption) {
        this.orderProductOptionId = orderProductOption.getOrderProductOptionId();
        this.optionId = orderProductOption.getOptionId();
        this.optionNm = orderProductOption.getOptionNm();
        this.optionPrice = orderProductOption.getOptionPrice();
        this.quantity = orderProductOption.getQuantity();
    }
}
