package com.gw.guposapi;

import com.gw.guposapi.app.product.adapter.persistence.repository.ProductCategoryRepository;
import com.gw.guposapi.app.product.adapter.persistence.repository.ProductOptionGroupRepository;
import com.gw.guposapi.app.product.application.in.ProductCategoryUseCase;
import com.gw.guposapi.app.product.application.in.ProductUseCase;
import com.gw.guposapi.app.product.application.out.ProductOptionPort;
import com.gw.guposcore.domain.product.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;
    private final ProductOptionGroupRepository productOptionGroupRepository;

    @PostConstruct
    public void init() {
        initService.createProductOption();
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
        private final ProductOptionGroupRepository productOptionGroupRepository;


        public void createProduct() {
            int price = 500;
            List<ProductCategory> all = productCategoryRepository.findAll();
            for(int i = 0; i < 30; i++) {
                Product createdProduct = Product.create("상품_" + i, "상품설명", "상품상세설명", price, "N", null);

                if(i < 15) {
                    createdProduct.withCategory(all.get(0));
                } else {
                    ProductOptionGroup optionGroup = productOptionGroupRepository.findById(1L).orElse(null);
                    ProductOptionRel productOptionRel = ProductOptionRel.create(createdProduct, optionGroup);
                    createdProduct.addProductOption(productOptionRel);

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

        public void createProductOption() {
            ProductOptionGroup createdOption = ProductOptionGroup.create("온도", "N", 1, 1);
            createdOption.addProductOptionDetail(ProductOption.create("ICE", 500, 1));
            createdOption.addProductOptionDetail(ProductOption.create("HOT", 0, 2));

            productOptionGroupRepository.save(createdOption);

            ProductOptionGroup createdOption2 = ProductOptionGroup.create("사이즈", "N", 1, 2);
            createdOption2.addProductOptionDetail(ProductOption.create("S", 0, 1));
            createdOption2.addProductOptionDetail(ProductOption.create("M", 500, 2));
            createdOption2.addProductOptionDetail(ProductOption.create("L", 1000, 3));

            productOptionGroupRepository.save(createdOption2);
        }
    }
}
