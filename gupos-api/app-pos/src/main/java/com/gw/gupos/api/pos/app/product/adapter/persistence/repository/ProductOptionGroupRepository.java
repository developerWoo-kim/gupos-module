package com.gw.gupos.api.pos.app.product.adapter.persistence.repository;

import com.gw.guposcore.domain.product.ProductOptionGroup;
import com.gw.guposcore.domain.product.repository.ProductOptionGroupCoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionGroupRepository extends JpaRepository<ProductOptionGroup, Long>, ProductOptionGroupCoreRepository {
}
