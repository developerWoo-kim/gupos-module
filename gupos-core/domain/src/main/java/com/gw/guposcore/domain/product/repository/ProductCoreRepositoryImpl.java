package com.gw.guposcore.domain.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductCoreRepositoryImpl implements ProductCoreRepository{
    private final JPAQueryFactory queryFactory;
}
