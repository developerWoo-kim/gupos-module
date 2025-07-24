package com.gw.guposapi.app.product.application.out;

import com.gw.guposcore.domain.product.ProductCategory;

import java.util.List;

public interface ProductCategoryPort {
    ProductCategory save(ProductCategory productCategory);

    void delete(Long categoryId);

    int findMaxSortOrder();
    ProductCategory findById(Long categoryId);
    List<ProductCategory> findCategoryListWithProducts();

}
