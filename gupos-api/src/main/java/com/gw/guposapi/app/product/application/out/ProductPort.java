package com.gw.guposapi.app.product.application.out;

import com.gw.guposcore.domain.product.Product;

import java.util.List;

public interface ProductPort {
    Product save(Product product);
    Product findById(Long productId);
    List<Product> findList();
    List<Product> findProductListWithOptionGroup();
}
