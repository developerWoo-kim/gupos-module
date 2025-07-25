package com.gw.guposapi.app.product.application.service;

import com.gw.guposapi.app.product.adapter.web.request.UpdateCategoryProductRequest;
import com.gw.guposapi.app.product.adapter.web.request.UpdateCategorySortOrderRequest;
import com.gw.guposapi.app.product.application.in.ProductCategoryUseCase;
import com.gw.guposapi.app.product.application.out.ProductCategoryPort;
import com.gw.guposcore.domain.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.gw.guposapi.app.product.adapter.web.request.UpdateCategorySortOrderRequest.*;

@Service
@Transactional
@RequiredArgsConstructor
class ProductCategoryService implements ProductCategoryUseCase {
    private final ProductCategoryPort productCategoryPort;

    @Override
    public ProductCategory createCategory(String categoryNm) {
        int maxSortOrder = productCategoryPort.findMaxSortOrder();
        return productCategoryPort.save(ProductCategory.create(categoryNm, maxSortOrder + 1));
    }

    @Override
    public ProductCategory updateCategory(UpdateCategoryProductRequest request) {
        ProductCategory foundCategory = productCategoryPort.findById(request.getCategoryId());
        foundCategory.updateCategoryNm(request.getCategoryNm());
        return foundCategory;
    }

    @Override
    public void updateSortOrder(UpdateCategorySortOrderRequest request) {
        for (CategorySortOrderRequest categorySortOrder : request.getCategorySortOrderList()) {
            ProductCategory foundCategory = productCategoryPort.findById(categorySortOrder.getCategoryId());
            foundCategory.updateSortOrder(categorySortOrder.getSortOrder());
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        productCategoryPort.delete(categoryId);
    }

    @Override
    public List<ProductCategory> getCategoryListWithProducts() {
        List<ProductCategory> categoryList = productCategoryPort.findCategoryListWithProducts();
        for (ProductCategory category : categoryList) {
            for (Product product : category.getProductList()) {
                for (ProductOptionRel productOptionRel : product.getProductOptionRelList()) {
                    for (ProductOption option : productOptionRel.getProductOptionGroup().getProductOptionList()) {
                        Long productOptionId = option.getProductOptionId();
                    }
                };

            }
        }
        return categoryList;
    }
}
