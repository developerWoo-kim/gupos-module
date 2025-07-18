package com.gw.guposapi.app.product.adapter.persistence;

import com.gw.guposapi.app.product.adapter.persistence.repository.ProductRepository;
import com.gw.guposapi.app.product.application.out.ProductPort;
import com.gw.guposcore.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductPersistence implements ProductPort {
    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> findList() {
        return productRepository.findList();
    }
}
