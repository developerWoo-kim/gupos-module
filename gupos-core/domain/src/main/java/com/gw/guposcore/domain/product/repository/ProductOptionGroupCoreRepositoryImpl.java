package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.ProductOptionGroup;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.gw.guposcore.domain.product.QProductOption.productOption;
import static com.gw.guposcore.domain.product.QProductOptionGroup.productOptionGroup;

@RequiredArgsConstructor
public class ProductOptionGroupCoreRepositoryImpl implements ProductOptionGroupCoreRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public ProductOptionGroup findOptionGroupById(Long optionGroupId) {
        return queryFactory
                .select(productOptionGroup)
                .from(productOptionGroup)
                .leftJoin(productOptionGroup.productOptionList, productOption).fetchJoin()
                .where(productOptionGroup.productOptionGroupId.eq(optionGroupId))
                .orderBy(productOption.sortOrder.asc())
                .fetchOne();
    }

    @Override
    public List<ProductOptionGroup> findOptionGroupList() {
        return queryFactory
                .select(productOptionGroup)
                .from(productOptionGroup)
                .leftJoin(productOptionGroup.productOptionList, productOption).fetchJoin()
                .orderBy(productOptionGroup.sortOrder.asc())
                .fetch();
    }

    @Override
    public int findMaxSortOrder() {
        return Optional.ofNullable(queryFactory
                .select(productOptionGroup.sortOrder.max())
                .from(productOptionGroup)
                .fetchOne()
        ).orElse(0);
    }
}
