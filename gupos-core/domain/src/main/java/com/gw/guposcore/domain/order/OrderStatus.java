package com.gw.guposcore.domain.order;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PROGRESS("진행"),
    COMPLETE("완료"),
    CANCEL("취소"),
    ;

    private final String label;

    OrderStatus(String label) {
        this.label = label;
    }
}
