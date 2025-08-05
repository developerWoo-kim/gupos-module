package com.gw.gupos.api.pos.app.product.application.in;

import com.gw.gupos.api.pos.app.product.adapter.web.request.CreateProductOptionGroupRequest;
import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;

import java.util.List;

public interface ProductOptionUseCase {
    ProductOption getOptionById(Long optionId);

    List<ProductOptionGroup> getOptionGroupList();

    ProductOptionGroup createOptionGroup(CreateProductOptionGroupRequest request);
}
