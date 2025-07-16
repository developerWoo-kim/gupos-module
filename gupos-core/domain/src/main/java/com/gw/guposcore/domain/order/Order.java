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
@Entity
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id")
    private String orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProductList = new ArrayList<>();

    private LocalDateTime orderDateTime;

    @Builder
    public Order(String orderId, OrderStatus orderStatus, OrderType orderType, LocalDateTime orderDateTime) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.orderDateTime = orderDateTime;
    }
}
