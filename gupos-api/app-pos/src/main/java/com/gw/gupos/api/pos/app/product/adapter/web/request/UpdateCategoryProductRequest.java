package com.gw.gupos.api.pos.app.product.adapter.web.request;

import lombok.Data;

@Data
public class UpdateCategoryProductRequest {
    private Long categoryId;
    private String categoryNm;

}
