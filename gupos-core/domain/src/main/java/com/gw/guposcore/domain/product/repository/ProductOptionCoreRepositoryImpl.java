package com.gw.guposcore.domain.product.repository;

import com.gw.guposcore.domain.product.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gw.guposcore.domain.product.QProduct.product;
import static com.gw.guposcore.domain.product.QProductOption.productOption;
import static com.gw.guposcore.domain.product.QProductOptionGroup.productOptionGroup;


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

    @Override
    public List<ProductOptionGroup> findOptionGroupListByProductId(Long productId) {
        return queryFactory
                .select(productOptionGroup)
                .from(productOptionGroup)
                .join(productOptionGroup.product, product).fetchJoin()
                .join(productOptionGroup.productOptionList, productOption).fetchJoin()
                .where(
                        productOptionGroup.product.productId.eq(productId)
                )
                .fetch();
    }
}
