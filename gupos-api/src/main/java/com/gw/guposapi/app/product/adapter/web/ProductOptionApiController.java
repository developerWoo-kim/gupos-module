package com.gw.guposapi.app.product.adapter.web;

import com.gw.guposapi.app.product.adapter.web.request.CreateProductOptionGroupRequest;
import com.gw.guposapi.app.product.adapter.web.response.ProductOptionGroupDto;
import com.gw.guposapi.app.product.application.in.ProductOptionUseCase;

import com.gw.guposcore.domain.product.ProductOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductOptionApiController {
    private final ProductOptionUseCase productOptionUseCase;

    @GetMapping("/api/v1/pos/product/option/group/list")
    public List<ProductOptionGroupDto> getProductList() {
        List<ProductOptionGroup> optionGroupList = productOptionUseCase.getOptionGroupList();
        return ProductOptionGroupDto.createList(optionGroupList);
    }

    @PostMapping("/api/v1/pos/product/option/group")
    public ProductOptionGroupDto createProductOptionGroupList(@RequestBody CreateProductOptionGroupRequest request) {
        ProductOptionGroup optionGroup = productOptionUseCase.createOptionGroup(request);

        ProductOptionGroupDto productOptionGroupDto = new ProductOptionGroupDto(optionGroup);

        return productOptionGroupDto;
    }
}
