package com.gw.gupos.api.pos.app.product.application.service;

import com.gw.gupos.api.pos.app.product.adapter.web.request.CreateProductOptionGroupRequest;
import com.gw.gupos.api.pos.app.product.adapter.web.request.CreateProductOptionGroupRequest.CreateProductOptionRequest;
import com.gw.gupos.api.pos.app.product.application.in.ProductOptionUseCase;
import com.gw.gupos.api.pos.app.product.application.out.ProductOptionPort;
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

    @Override
    public ProductOptionGroup createOptionGroup(CreateProductOptionGroupRequest request) {
        int optionGroupMaxSortOrder = productOptionPort.findOptionGroupMaxSortOrder();
        ProductOptionGroup optionGroup = ProductOptionGroup.create(request.getOptionGroupNm(), request.getRequiredAt(), request.getMaxSelectionCount(), optionGroupMaxSortOrder + 1);

        int optionSortOrder = 1;
        for (CreateProductOptionRequest option : request.getOptionList()) {
            optionGroup.addProductOption(ProductOption.create(option.getProductOptionNm(), option.getOptionPrice(), optionSortOrder++));
        }

        ProductOptionGroup savedOptionGroup = productOptionPort.saveOptionGroup(optionGroup);

        return savedOptionGroup;
    }
}
