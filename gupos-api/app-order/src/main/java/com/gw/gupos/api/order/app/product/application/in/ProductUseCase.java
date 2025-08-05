package com.gw.gupos.api.order.app.product.application.in;

import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;

import java.util.List;

public interface ProductUseCase {
    Product getProduct(Long productId);

    List<Product> getProductList();
    List<ProductCategory> getProductCategoryList();
    List<ProductCategory> getProductCategoriesWithProducts();
}
