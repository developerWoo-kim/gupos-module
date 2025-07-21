package com.gw.guposapi.app.product.application.out;

import com.gw.guposcore.domain.product.ProductOption;

public interface ProductOptionPort {
    ProductOption findOptionById(Long optionId);
}
