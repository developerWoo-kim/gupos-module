package com.gw.gupos.api.order.app.product.adapter.persistence;

import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;
import com.gw.gupos.api.order.app.product.adapter.persistence.repository.ProductCategoryRepository;
import com.gw.gupos.api.order.app.product.adapter.persistence.repository.ProductRepository;
import com.gw.gupos.api.order.app.product.application.out.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductPersistence implements ProductPort {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public Product findProductWithOptions(Long productId) {
        return productRepository.findProductWithOptions(productId);
    }

    @Override
    public List<Product> findProductList() {
        return productRepository.findList();
    }

    @Override
    public List<ProductCategory> findCategoryList() {
        return productCategoryRepository.findCategoryList();
    }

    @Override
    public List<ProductCategory> findCategoriesWithProducts() {
        return productCategoryRepository.findCategoryListWithProducts();
    }
}
