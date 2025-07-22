package com.gw.guposapi.app.product.application.service;

import com.gw.guposapi.app.product.application.in.ProductCategoryUseCase;
import com.gw.guposapi.app.product.application.out.ProductCategoryPort;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;
import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductCategoryService implements ProductCategoryUseCase {
    private final ProductCategoryPort productCategoryPort;

    @Override
    public ProductCategory createCategory(String categoryNm) {
        int maxSortOrder = productCategoryPort.findMaxSortOrder();
        return productCategoryPort.save(ProductCategory.create(categoryNm, maxSortOrder + 1));
    }

    @Override
    public List<ProductCategory> getCategoryListWithProducts() {
        List<ProductCategory> categoryList = productCategoryPort.findCategoryListWithProducts();
        for (ProductCategory category : categoryList) {
            for (Product product : category.getProductList()) {
                for (ProductOptionGroup optionGroup : product.getProductOptionGroupList()) {
                    for (ProductOption productOption : optionGroup.getProductOptionList()) {
                        Long productOptionId = productOption.getProductOptionId();
                    }
                }
            }
        }
        return categoryList;
    }
}
