package com.gw.guposapi.app.product.application.in;

import com.gw.guposcore.domain.product.ProductCategory;

import java.util.List;

public interface ProductCategoryUseCase {

    ProductCategory createCategory(String categoryNm);
    List<ProductCategory> getCategoryListWithProducts();
}
