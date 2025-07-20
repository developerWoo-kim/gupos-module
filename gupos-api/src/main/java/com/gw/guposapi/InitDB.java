package com.gw.guposapi;

import com.gw.guposapi.app.product.application.in.ProductUseCase;
import com.gw.guposcore.domain.product.Product;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.createProduct();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class InitService {
        private final ProductUseCase productUseCase;


        public void createProduct() {
            int price = 500;
            for(int i = 0; i < 30; i++) {
                Product product1 = productUseCase.createProduct(Product.create("상품_" + i, "상품설명", "상품상세설명", price, "N", null));
                productUseCase.createProduct(product1);
                price += 500;
            }
//            Product product1 = productUseCase.createProduct(Product.create("상품1", "상품설명", "상품상세설명", 1000, "N", null));
//            productUseCase.createProduct(product1);
//            Product product2 = productUseCase.createProduct(Product.create("상품2", "상품설명", "상품상세설명", 2000, "N", null));
//            productUseCase.createProduct(product2);
//            Product product3 = productUseCase.createProduct(Product.create("상품3", "상품설명", "상품상세설명", 2500, "N", null));
//            productUseCase.createProduct(product3);
        }
    }
}
