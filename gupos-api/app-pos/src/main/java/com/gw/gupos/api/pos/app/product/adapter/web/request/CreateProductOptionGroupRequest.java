package com.gw.gupos.api.pos.app.product.adapter.web.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateProductOptionGroupRequest {
    private String optionGroupNm;
    private String requiredAt;
    private int maxSelectionCount;

    private List<CreateProductOptionRequest> optionList;

    @Data
    public static class CreateProductOptionRequest {
        private String productOptionNm;
        private int optionPrice;
    }
}
