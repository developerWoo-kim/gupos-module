package com.gw.guposapi.app.product.adapter.web;

import com.gw.guposapi.app.order.adapter.web.response.OrderDto;
import com.gw.guposapi.app.product.adapter.web.response.ProductDto;
import com.gw.guposapi.app.product.adapter.web.response.ProductOptionGroupDto;
import com.gw.guposapi.app.product.application.in.ProductOptionUseCase;
import com.gw.guposapi.app.product.application.in.ProductUseCase;
import com.gw.guposcore.domain.order.Order;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductUseCase productUseCase;

    @GetMapping("/api/v1/pos/product/list")
    public List<ProductDto> getProductList() {
        List<Product> orderList = productUseCase.getProductListWithOptions();
        return ProductDto.createList(orderList);
    }

}
