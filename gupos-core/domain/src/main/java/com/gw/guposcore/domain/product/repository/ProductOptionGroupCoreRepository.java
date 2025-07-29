package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.ProductOptionGroup;

import java.util.List;

public interface ProductOptionGroupCoreRepository {

    ProductOptionGroup findOptionGroupById(Long optionGroupId);
    List<ProductOptionGroup> findOptionGroupList();

    int findMaxSortOrder();
}
