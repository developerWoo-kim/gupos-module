package com.gw.guposapi.app.product.application.in;

import com.gw.guposcore.domain.product.Product;

import java.util.List;

public interface ProductUseCase {
    Product createProduct(Product product);

    Product getProductById(Long productId);
    List<Product> getProductList();
    List<Product> getProductListWithOptions();
}
