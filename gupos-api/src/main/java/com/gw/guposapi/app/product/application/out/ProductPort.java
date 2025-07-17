package com.gw.guposapi.app.product.application.out;

import com.gw.guposcore.domain.product.Product;

public interface ProductPort {
    Product save(Product product);
    Product findById(Long productId);
}
