package com.gw.guposapi.app.product.application.service;

import com.gw.guposapi.app.product.application.in.ProductUseCase;
import com.gw.guposapi.app.product.application.out.ProductPort;
import com.gw.guposcore.domain.product.Product;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements ProductUseCase {
    private final ProductPort productPort;

    @Override
    public Product createProduct(Product product) {
        return productPort.save(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return null;
    }
}
