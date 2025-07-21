package com.gw.guposcore.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gw.guposcore.domain.product.Product;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity(name = "tb_od_order_product")
@NoArgsConstructor
public class OrderProduct {

    @Id
    @Column(name = "order_product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int orderProductPrice;
    private int quantity;

    @JsonIgnore
    @OneToMany(mappedBy = "orderProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProductOption> orderProductOptionList = new ArrayList<>();

    @Builder()
    public OrderProduct(Order order, Product product, int orderProductPrice, int quantity) {
        this.order = order;
        this.product = product;
        this.orderProductPrice = orderProductPrice;
        this.quantity = quantity;
    }

    public static OrderProduct create(Product product, int orderProductPrice, int quantity) {
        return builder()
                .product(product)
                .orderProductPrice(orderProductPrice)
                .quantity(quantity)
                .build();
    }

    public void addOption(OrderProductOption orderProductOption) {
        orderProductOption.withOrderProduct(this);
        orderProductOptionList.add(orderProductOption);
    }

    public void withOrder(Order order) {
        this.order = order;
    }

}
