package com.gw.guposapi.ws.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {
    private String storeId;
    private String content;
}
