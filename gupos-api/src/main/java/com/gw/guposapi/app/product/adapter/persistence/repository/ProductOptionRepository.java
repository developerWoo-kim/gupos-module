package com.gw.guposapi.app.product.adapter.persistence.repository;

import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.repository.ProductOptionCoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepository extends JpaRepository<ProductOption, Long>, ProductOptionCoreRepository {
}
