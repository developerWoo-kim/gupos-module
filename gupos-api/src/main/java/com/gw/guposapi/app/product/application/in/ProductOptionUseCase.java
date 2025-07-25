package com.gw.guposapi.app.product.application.in;

import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;

import java.util.List;

public interface ProductOptionUseCase {
    ProductOption getOptionById(Long optionId);

    List<ProductOptionGroup> getOptionGroupList();
}
