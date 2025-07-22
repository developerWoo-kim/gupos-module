package com.gw.guposapi.app.product.adapter.persistence;

import com.gw.guposapi.app.product.adapter.persistence.repository.ProductCategoryRepository;
import com.gw.guposapi.app.product.application.out.ProductCategoryPort;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductCategoryPersistence implements ProductCategoryPort {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findCategoryListWithProducts() {
        return productCategoryRepository.findCategoryListWithProducts();
    }

    @Override
    public int findMaxSortOrder() {
        return productCategoryRepository.findMaxSortOrder();
    }
}
