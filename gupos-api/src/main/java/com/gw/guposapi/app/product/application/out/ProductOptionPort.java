package com.gw.guposapi.app.product.application.out;

import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;

import java.util.List;

public interface ProductOptionPort {
    ProductOptionGroup save(ProductOptionGroup productOptionGroup);
    List<ProductOptionGroup> findOptionGroupList();
    ProductOption findOptionById(Long optionId);
}
