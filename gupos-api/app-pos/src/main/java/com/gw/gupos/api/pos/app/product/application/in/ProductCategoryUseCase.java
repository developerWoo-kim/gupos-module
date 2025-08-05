package com.gw.gupos.api.pos.app.product.application.in;

import com.gw.gupos.api.pos.app.product.adapter.web.request.UpdateCategoryProductRequest;
import com.gw.gupos.api.pos.app.product.adapter.web.request.UpdateCategorySortOrderRequest;
import com.gw.guposcore.domain.product.ProductCategory;

import java.util.List;

public interface ProductCategoryUseCase {

    ProductCategory createCategory(String categoryNm);

    ProductCategory updateCategory(UpdateCategoryProductRequest request);

    void updateSortOrder(UpdateCategorySortOrderRequest request);

    void deleteCategory(Long categoryId);

    List<ProductCategory> getCategoryListWithProducts();
}
