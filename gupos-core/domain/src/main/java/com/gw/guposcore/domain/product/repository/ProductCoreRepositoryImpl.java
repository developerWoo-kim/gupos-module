package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gw.guposcore.domain.product.QProduct.product;
import static com.gw.guposcore.domain.product.QProductOption.productOption;
import static com.gw.guposcore.domain.product.QProductOptionGroup.productOptionGroup;
import static com.gw.guposcore.domain.product.QProductOptionRel.productOptionRel;

@RequiredArgsConstructor
public class ProductCoreRepositoryImpl implements ProductCoreRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Product> findList() {
        return queryFactory
                .select(product)
                .from(product)
                .fetch();
    }

    @Override
    public List<Product> findProductWithOptions() {
        return queryFactory
                .select(product)
                .from(product)
                .leftJoin(product.productOptionRelList, productOptionRel).fetchJoin()
                .leftJoin(productOptionRel.productOptionGroup, productOptionGroup).fetchJoin()
                .fetch();
    }
}
