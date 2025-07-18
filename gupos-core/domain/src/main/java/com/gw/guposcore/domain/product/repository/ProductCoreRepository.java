package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.Product;

import java.util.List;

public interface ProductCoreRepository {
    List<Product> findList();
}
