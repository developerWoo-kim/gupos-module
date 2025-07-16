package com.gw.guposcore.domain.order;

import lombok.Getter;

@Getter
public enum OrderType {
    DINE_IN("매장"),
    TAKE_OUT("포장"),
    DELIVERY("배달"),
    ;

    private final String label;

    OrderType(String label) {
        this.label = label;
    }
}
