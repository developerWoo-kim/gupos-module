package com.gw.gupos.api.order.app.product.application.out;

import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;

import java.util.List;

public interface ProductPort {
    Product findProductWithOptions(Long productId);

    List<Product> findProductList();
    List<ProductCategory> findCategoryList();
    List<ProductCategory> findCategoriesWithProducts();
}
