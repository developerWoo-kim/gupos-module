package com.gw.guposapi.app.product.adapter.web;

import com.gw.guposapi.app.order.adapter.web.response.OrderDto;
import com.gw.guposapi.app.product.adapter.web.request.CreateProductRequest;
import com.gw.guposapi.app.product.adapter.web.response.ProductDto;
import com.gw.guposapi.app.product.adapter.web.response.ProductOptionGroupDto;
import com.gw.guposapi.app.product.application.in.ProductOptionUseCase;
import com.gw.guposapi.app.product.application.in.ProductUseCase;
import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/pos/product")
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductUseCase productUseCase;

    @GetMapping("/list")
    public List<ProductDto> getProductList() {
        List<Product> orderList = productUseCase.getProductListWithOptions();
        return ProductDto.createList(orderList);
    }

    @PostMapping("/create")
    public ProductDto createProduct(@RequestBody CreateProductRequest request) {
        Product savedProduct = productUseCase.createProduct(request);
        return new ProductDto(savedProduct);
    }

}
