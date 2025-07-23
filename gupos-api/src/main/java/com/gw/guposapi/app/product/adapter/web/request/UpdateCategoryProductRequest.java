package com.gw.guposapi.app.product.adapter.web.request;

import lombok.Data;

@Data
public class UpdateCategoryProductRequest {
    private Long categoryId;
    private String categoryNm;

}
