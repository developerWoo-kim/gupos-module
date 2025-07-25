package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.ProductOptionGroup;

import java.util.List;

public interface ProductOptionGroupCoreRepository {
    List<ProductOptionGroup> findOptionGroupList();
}
