package com.gw.guposapi.app.product.adapter.web.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateProductRequest {
    private String productNm;
    private Long categoryId;
    private List<Long> optionGroupIds;
    private int productPrice;
    private String stockAt;
    private int stockCount;

    public CreateProductRequest(String productNm, Long categoryId, List<Long> optionGroupIds, int productPrice, String stockAt, int stockCount) {
        this.productNm = productNm;
        this.categoryId = categoryId;
        this.optionGroupIds = optionGroupIds;
        this.productPrice = productPrice;
        this.stockAt = stockAt;
        this.stockCount = stockCount;
    }
}
