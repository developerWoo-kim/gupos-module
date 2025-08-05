package com.gw.gupos.api.pos.app.product.application.in;

import com.gw.gupos.api.pos.app.product.adapter.web.request.CreateProductRequest;
import com.gw.guposcore.domain.product.Product;

import java.util.List;

public interface ProductUseCase {
    Product createProduct(Product product);
    Product createProduct(CreateProductRequest request);

    Product getProductById(Long productId);
    List<Product> getProductList();
    List<Product> getProductListWithOptions();
}
