package com.gw.guposapi.app.product.application.in;

import com.gw.guposapi.app.product.adapter.web.request.UpdateCategoryProductRequest;
import com.gw.guposcore.domain.product.ProductCategory;

import java.util.List;

public interface ProductCategoryUseCase {

    ProductCategory createCategory(String categoryNm);

    ProductCategory updateCategory(UpdateCategoryProductRequest request);

    List<ProductCategory> getCategoryListWithProducts();
}
