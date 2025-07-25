package com.gw.guposapi.app.product.adapter.web.response;

import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductOptionGroupDto {
    private Long productOptionGroupId;
    private String productOptionGroupNm;
    private String requiredAt;
    private int maxSelectionCount;
    private int sortOrder;

    private List<ProductOptionDto> optionList = new ArrayList<>();

    public static List<ProductOptionGroupDto> createList(List<ProductOptionGroup> productOptionGroups) {
        List<ProductOptionGroupDto> productOptionGroupDtoList = new ArrayList<>();
        for (ProductOptionGroup productOptionGroup : productOptionGroups) {
            productOptionGroupDtoList.add(new ProductOptionGroupDto(productOptionGroup));
        }
        return productOptionGroupDtoList;
    }

    public ProductOptionGroupDto(ProductOptionGroup productOptionGroup) {
        this.productOptionGroupId = productOptionGroup.getProductOptionGroupId();
        this.productOptionGroupNm = productOptionGroup.getProductOptionGroupNm();
        this.requiredAt = productOptionGroup.getRequiredAt();
        this.maxSelectionCount = productOptionGroup.getMaxSelectionCount();
        this.sortOrder = productOptionGroup.getSortOrder();

        if (Hibernate.isInitialized(productOptionGroup.getProductOptionList()) && productOptionGroup.getProductOptionList() != null) {
            for (ProductOption productOption : productOptionGroup.getProductOptionList()) {
                this.optionList.add(new ProductOptionDto(productOption));
            }
        }
    }

    @Data
    @NoArgsConstructor
    public static class ProductOptionDto {
        private Long productOptionId;
        private String productOptionNm;
        private int optionPrice;
        private int sortOrder;

        public ProductOptionDto(ProductOption productOption) {
            this.productOptionId = productOption.getProductOptionId();
            this.productOptionNm = productOption.getProductOptionNm();
            this.optionPrice = productOption.getOptionPrice();
            this.sortOrder = productOption.getSortOrder();
        }
    }
}
