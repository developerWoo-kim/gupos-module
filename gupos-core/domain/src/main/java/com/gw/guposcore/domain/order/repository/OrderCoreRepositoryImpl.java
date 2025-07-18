package com.gw.guposcore.domain.order.repository;

import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.product.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.gw.guposcore.domain.order.QOrder.order;
import static com.gw.guposcore.domain.order.QOrderProduct.orderProduct;
import static com.gw.guposcore.domain.product.QProduct.product;

@RequiredArgsConstructor
public class OrderCoreRepositoryImpl implements OrderCoreRepository{
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Order> findList() {
        return queryFactory
                .select(order)
                .from(order)
                .join(order.orderProductList, orderProduct).fetchJoin()
                .join(orderProduct.product, product).fetchJoin()
                .fetch();
    }
}
