package com.gw.guposcore.domain.order.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderCoreRepositoryImpl implements OrderCoreRepository{
    private final JPAQueryFactory queryFactory;

}
