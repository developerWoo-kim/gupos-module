package com.gw.guposapi.app.product.application.out;

import com.gw.guposcore.domain.product.ProductCategory;

import java.util.List;

public interface ProductCategoryPort {
    ProductCategory save(ProductCategory productCategory);

    int findMaxSortOrder();
    ProductCategory findById(Long categoryId);
    List<ProductCategory> findCategoryListWithProducts();


}
