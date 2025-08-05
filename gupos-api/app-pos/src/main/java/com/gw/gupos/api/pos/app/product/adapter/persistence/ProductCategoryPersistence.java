package com.gw.gupos.api.pos.app.product.adapter.persistence;

import com.gw.gupos.api.pos.app.product.adapter.persistence.repository.ProductCategoryRepository;
import com.gw.gupos.api.pos.app.product.application.out.ProductCategoryPort;
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
    public void delete(Long categoryId) {
        productCategoryRepository.findById(categoryId);
    }

    @Override
    public List<ProductCategory> findCategoryListWithProducts() {
        return productCategoryRepository.findCategoryListWithProducts();
    }

    @Override
    public int findMaxSortOrder() {
        return productCategoryRepository.findMaxSortOrder();
    }

    @Override
    public ProductCategory findById(Long categoryId) {
        return productCategoryRepository.findById(categoryId).orElse(null);
    }
}
