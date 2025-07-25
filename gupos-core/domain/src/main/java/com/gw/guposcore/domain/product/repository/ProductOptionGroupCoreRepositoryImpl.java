package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.ProductOptionGroup;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gw.guposcore.domain.product.QProductOption.productOption;
import static com.gw.guposcore.domain.product.QProductOptionGroup.productOptionGroup;

@RequiredArgsConstructor
public class ProductOptionGroupCoreRepositoryImpl implements ProductOptionGroupCoreRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductOptionGroup> findOptionGroupList() {
        return queryFactory
                .select(productOptionGroup)
                .from(productOptionGroup)
                .leftJoin(productOptionGroup.productOptionList, productOption).fetchJoin()
                .orderBy(productOptionGroup.sortOrder.asc())
                .fetch();
    }
}
