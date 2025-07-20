package com.gw.guposapi.app.order;

import com.gw.guposapi.app.order.adapter.web.request.CreateOrderRequest;
import com.gw.guposapi.app.order.application.in.OrderUseCase;
import com.gw.guposapi.app.product.application.in.ProductUseCase;
import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.order.OrderStatus;
import com.gw.guposcore.domain.order.OrderType;
import com.gw.guposcore.domain.product.Product;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
@ActiveProfiles(value = "local")
public class OrderServiceTest {
    @Autowired
    private OrderUseCase orderUseCase;
    @Autowired
    private ProductUseCase productUseCase;
    @Autowired
    private EntityManager entityManager;

//    @BeforeEach
//    public void init() {
//
//    }

    @Test
    void orderTest() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setOrderType(OrderType.DINE_IN);


        List<CreateOrderRequest.CreateOrderProductRequest> productRequestList = new ArrayList<>();
        Product product1 = productUseCase.createProduct(Product.create("상품1", "상품설명", "상품상세설명", 1000, "N", null));
        CreateOrderRequest.CreateOrderProductRequest productRequest1 = new CreateOrderRequest.CreateOrderProductRequest();
        productRequest1.setOrderProductPrice(product1.getProductPrice());
        productRequest1.setProductId(product1.getProductId());
        productRequest1.setQuantity(10);
        productRequestList.add(productRequest1);

        Product product2 = productUseCase.createProduct(Product.create("상품2", "상품설명", "상품상세설명", 2000, "N", null));
        entityManager.persist(product2);
        CreateOrderRequest.CreateOrderProductRequest productRequest2 = new CreateOrderRequest.CreateOrderProductRequest();
        productRequest2.setOrderProductPrice(product2.getProductPrice());
        productRequest2.setProductId(product2.getProductId());
        productRequest2.setQuantity(10);
        productRequestList.add(productRequest2);


        createOrderRequest.setOrderProductList(productRequestList);


        Order order = orderUseCase.createOrder(createOrderRequest);

        Assertions.assertEquals(2, order.getOrderProductList().size());
    }
}
