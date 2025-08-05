package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.ProductCategory;

import java.util.List;

public interface ProductCategoryCoreRepository {

    List<ProductCategory> findCategoryListWithProducts();

    List<ProductCategory> findCategoryList();

    int findMaxSortOrder();
}
