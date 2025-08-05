package com.gw.gupos.api.order.app.product.adapter.persistence.repository;

import com.gw.guposcore.domain.product.ProductCategory;
import com.gw.guposcore.domain.product.repository.ProductCategoryCoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>, ProductCategoryCoreRepository {
}
