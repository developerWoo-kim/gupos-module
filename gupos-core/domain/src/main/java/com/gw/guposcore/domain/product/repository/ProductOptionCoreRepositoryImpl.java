package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.gw.guposcore.domain.product.QProductOption.productOption;


@Repository
@RequiredArgsConstructor
public class ProductOptionCoreRepositoryImpl implements ProductOptionCoreRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public ProductOption findOptionById(Long optionId) {
        return queryFactory
                .select(productOption)
                .from(productOption)
                .where(
                        productOption.productOptionId.eq(optionId)
                )
                .fetchOne();
    }

}
