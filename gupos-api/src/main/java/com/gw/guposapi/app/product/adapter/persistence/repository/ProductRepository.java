package com.gw.guposapi.app.product.adapter.persistence.repository;

import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.repository.ProductCoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCoreRepository {
}
