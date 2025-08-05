package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.ProductCategory;
import com.gw.guposcore.domain.product.QProduct;
import com.gw.guposcore.domain.product.QProductCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static com.gw.guposcore.domain.product.QProduct.product;
import static com.gw.guposcore.domain.product.QProductCategory.productCategory;

@RequiredArgsConstructor
public class ProductCategoryCoreRepositoryImpl implements ProductCategoryCoreRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProductCategory> findCategoryListWithProducts() {
        return queryFactory
                .select(productCategory)
                .from(productCategory)
                .leftJoin(productCategory.productList, product).fetchJoin()
                .orderBy(productCategory.sortOrder.asc())
                .fetch();
    }

    @Override
    public List<ProductCategory> findCategoryList() {
        return queryFactory
                .select(productCategory)
                .from(productCategory)
                .orderBy(productCategory.sortOrder.asc())
                .fetch();
    }

    @Override
    public int findMaxSortOrder() {
        return Optional.ofNullable(
                queryFactory
                        .select(productCategory.sortOrder.max())
                        .from(productCategory)
                        .fetchOne()
        ).orElse(0);
    }
}
