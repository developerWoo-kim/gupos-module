package com.gw.guposapi.app.product.application.service;

import com.gw.guposapi.app.product.application.in.ProductUseCase;
import com.gw.guposapi.app.product.application.out.ProductPort;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionRel;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
class ProductService implements ProductUseCase {
    private final ProductPort productPort;

    @Override
    public Product createProduct(Product product) {
        return productPort.save(product);
    }

    @Override
    public List<Product> getProductList() {
        return productPort.findList();
    }

    @Override
    public List<Product> getProductListWithOptions() {
        List<Product> foundProduct = productPort.findProductListWithOptions();
        for (Product product : foundProduct) {
            for (ProductOptionRel optionRel : product.getProductOptionRelList()) {
                for (ProductOption productOption : optionRel.getProductOptionGroup().getProductOptionList()) {
                    Long productOptionId = productOption.getProductOptionId();
                }
            }
        }
        return foundProduct;
    }

    @Override
    public Product getProductById(Long productId) {
        return productPort.findById(productId);
    }
}
