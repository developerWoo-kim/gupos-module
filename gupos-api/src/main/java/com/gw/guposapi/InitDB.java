package com.gw.guposapi;

import com.gw.guposapi.app.product.adapter.persistence.repository.ProductCategoryRepository;
import com.gw.guposapi.app.product.application.in.ProductCategoryUseCase;
import com.gw.guposapi.app.product.application.in.ProductUseCase;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;
import com.gw.guposcore.domain.product.ProductOptionGroup;
import com.gw.guposcore.domain.product.ProductOption;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.createProductCategory();
        initService.createProduct();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class InitService {
        private final ProductUseCase productUseCase;
        private final ProductCategoryUseCase productCategoryUseCase;
        private final ProductCategoryRepository productCategoryRepository;


        public void createProduct() {
            int price = 500;
            List<ProductCategory> all = productCategoryRepository.findAll();
            for(int i = 0; i < 30; i++) {
                Product createdProduct = Product.create("상품_" + i, "상품설명", "상품상세설명", price, "N", null);

                ProductOptionGroup createdOption = ProductOptionGroup.create("온도_" + i, "N", 1, 1);
                createdOption.addProductOptionDetail(ProductOption.create("ICE", 500, 1));
                createdOption.addProductOptionDetail(ProductOption.create("HOT", 0, 2));
                createdProduct.addProductOption(createdOption);

                ProductOptionGroup createdOption2 = ProductOptionGroup.create("사이즈_" + i, "N", 1, 1);
                createdOption2.addProductOptionDetail(ProductOption.create("S", 0, 1));
                createdOption2.addProductOptionDetail(ProductOption.create("M", 500, 2));
                createdOption2.addProductOptionDetail(ProductOption.create("L", 1000, 3));
                createdProduct.addProductOption(createdOption2);


                if(i < 15) {
                    createdProduct.withCategory(all.get(0));
                } else {
                    createdProduct.withCategory(all.get(1));
                }

                productUseCase.createProduct(createdProduct);
                price += 500;
            }
        }

        public void createProductCategory() {
            productCategoryUseCase.createCategory("즐겨찾기");
            productCategoryUseCase.createCategory("음료");
            productCategoryUseCase.createCategory("음식");
        }
    }
}
