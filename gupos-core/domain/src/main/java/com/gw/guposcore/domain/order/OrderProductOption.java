package com.gw.guposcore.domain.order;

import com.gw.guposcore.domain.product.ProductOption;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "tb_od_order_product_option")
public class OrderProductOption {

    @Id
    @Column(name = "order_product_option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductOptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_product_id")
    private OrderProduct orderProduct;

    private Long optionId;
    private String optionNm;
    private int optionPrice;
    private int quantity;

    @Builder(toBuilder = true)
    public OrderProductOption(OrderProduct orderProduct, Long optionId, String optionNm, int optionPrice, int quantity) {
        this.orderProduct = orderProduct;
        this.optionId = optionId;
        this.optionNm = optionNm;
        this.optionPrice = optionPrice;
        this.quantity = quantity;
    }

    public static OrderProductOption create(ProductOption option, int quantity) {
        return builder()
                .optionId(option.getProductOptionId())
                .optionNm(option.getProductOptionNm())
                .optionPrice(option.getOptionPrice())
                .quantity(quantity)
                .build();
    }

    public void withOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }
}
