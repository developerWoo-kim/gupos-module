package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.ProductOption;

public interface ProductOptionCoreRepository {
    ProductOption findOptionById(Long optionId);
}
