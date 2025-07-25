package com.gw.guposapi.app.product.application.service;

import com.gw.guposapi.app.product.application.in.ProductOptionUseCase;
import com.gw.guposapi.app.product.application.out.ProductOptionPort;
import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ProductOptionService implements ProductOptionUseCase {
    private final ProductOptionPort productOptionPort;

    @Override
    public ProductOption getOptionById(Long optionId) {
        return productOptionPort.findOptionById(optionId);
    }

    @Override
    public List<ProductOptionGroup> getOptionGroupList() {
        return productOptionPort.findOptionGroupList();
    }
}
