package com.gw.guposapi.app.product.adapter.web.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateCategorySortOrderRequest {
    private List<CategorySortOrderRequest> categorySortOrderList;

    @Data
    public static class CategorySortOrderRequest {
        private Long categoryId;
        private int sortOrder;
    }
}


