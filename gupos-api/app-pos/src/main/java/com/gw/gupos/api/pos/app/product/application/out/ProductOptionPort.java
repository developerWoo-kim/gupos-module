package com.gw.gupos.api.pos.app.product.application.out;

import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;

import java.util.List;

public interface ProductOptionPort {
    ProductOptionGroup findOptionGroupById(Long optionGroupId);
    ProductOptionGroup saveOptionGroup(ProductOptionGroup productOptionGroup);
    List<ProductOptionGroup> findOptionGroupList();
    ProductOption findOptionById(Long optionId);

    int findOptionGroupMaxSortOrder();
}
