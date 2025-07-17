package com.gw.guposapi.app.product.application.in;

import com.gw.guposcore.domain.product.Product;

public interface ProductUseCase {
    Product createProduct(Product product);

    Product getProductById(Long productId);
}
