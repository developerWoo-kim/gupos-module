package com.gw.guposcore.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "tb_od_order")
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProductList = new ArrayList<>();

    private LocalDateTime orderDateTime;

    @Builder
    public Order(OrderStatus orderStatus, OrderType orderType, LocalDateTime orderDateTime) {
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.orderDateTime = orderDateTime;
    }

    public static Order create(OrderType orderType) {
        return builder()
                .orderStatus(OrderStatus.PROGRESS)
                .orderType(orderType)
                .orderDateTime(LocalDateTime.now())
                .build();
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProduct.withOrder(this);
        orderProductList.add(orderProduct);
    }

    public void complete() {
        this.orderStatus = OrderStatus.COMPLETE;
    }

    public void cancel() {
        this.orderStatus = OrderStatus.CANCEL;
    }

    public void cancelCompleted() {
        this.orderStatus = OrderStatus.PROGRESS;
    }
}
