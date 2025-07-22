package com.gw.guposapi.app.product.adapter.web;

import com.gw.guposapi.app.product.adapter.web.response.ProductCategoryDto;
import com.gw.guposapi.app.product.adapter.web.response.ProductDto;
import com.gw.guposapi.app.product.application.in.ProductCategoryUseCase;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductCategoryApiController {
    private final ProductCategoryUseCase productCategoryUseCase;

    @GetMapping("/api/v1/pos/categories/products/list")
    public List<ProductCategoryDto> getProductList() {
        List<ProductCategory> categoryList = productCategoryUseCase.getCategoryListWithProducts();
        return ProductCategoryDto.createList(categoryList);
    }
}
