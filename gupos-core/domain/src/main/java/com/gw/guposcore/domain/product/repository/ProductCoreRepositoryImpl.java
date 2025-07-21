package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.QProduct;
import com.gw.guposcore.domain.product.QProductOption;
import com.gw.guposcore.domain.product.QProductOptionGroup;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gw.guposcore.domain.product.QProduct.product;
import static com.gw.guposcore.domain.product.QProductOption.productOption;
import static com.gw.guposcore.domain.product.QProductOptionGroup.productOptionGroup;

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
    public List<Product> findProductWithOptionGroup() {
        return queryFactory
                .select(product)
                .from(product)
                .leftJoin(product.productOptionGroupList, productOptionGroup).fetchJoin()
                .fetch();
    }
}
