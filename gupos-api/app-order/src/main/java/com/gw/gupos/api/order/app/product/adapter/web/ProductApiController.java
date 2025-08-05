package com.gw.gupos.api.order.app.product.adapter.web;

import com.gw.guposcore.domain.product.Product;
import com.gw.gupos.api.order.app.product.adapter.web.response.ProductDto;
import com.gw.gupos.api.order.app.product.application.in.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gw.gupos.api.order.app.product.adapter.web.response.ProductDto.createList;

@RestController
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductUseCase productUseCase;

    @GetMapping("/api/v1/product/list")
    public List<ProductDto> getProductList() {
        List<Product> productList = productUseCase.getProductList();
        productUseCase.getProductCategoryList();
        return createList(productList);
    }
}
