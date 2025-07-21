package com.gw.guposapi.app.product.adapter.persistence;

import com.gw.guposapi.app.product.adapter.persistence.repository.ProductOptionRepository;
import com.gw.guposapi.app.product.application.out.ProductOptionPort;
import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductOptionPersistence implements ProductOptionPort {
    private final ProductOptionRepository productOptionRepository;

    @Override
    public ProductOption findOptionById(Long optionId) {
        return productOptionRepository.findOptionById(optionId);
    }
}
