package com.gw.gupos.api.order.app.product.application.service;

import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;
import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionRel;
import com.gw.gupos.api.order.app.product.application.in.ProductUseCase;
import com.gw.gupos.api.order.app.product.application.out.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
class ProductService implements ProductUseCase {
    private final ProductPort productPort;

    @Override
    public Product getProduct(Long productId) {
        Product foundProduct = productPort.findProductWithOptions(productId);
        for (ProductOptionRel productOptionRel : foundProduct.getProductOptionRelList()) {
            for (ProductOption option : productOptionRel.getProductOptionGroup().getProductOptionList()) {
                Long productOptionId = option.getProductOptionId();
            }
        };
        return foundProduct;
    }

    @Override
    public List<Product> getProductList() {
        return productPort.findProductList();
    }

    @Override
    public List<ProductCategory> getProductCategoryList() {
        return productPort.findCategoryList();
    }

    @Override
    public List<ProductCategory> getProductCategoriesWithProducts() {
        return productPort.findCategoriesWithProducts();
    }
}
