package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gw.guposcore.domain.product.QProduct.product;

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
}
