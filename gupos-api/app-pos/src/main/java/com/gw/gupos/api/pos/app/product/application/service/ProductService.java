package com.gw.gupos.api.pos.app.product.application.service;

import com.gw.gupos.api.pos.app.product.adapter.web.request.CreateProductRequest;
import com.gw.gupos.api.pos.app.product.application.in.ProductUseCase;
import com.gw.gupos.api.pos.app.product.application.out.ProductCategoryPort;
import com.gw.gupos.api.pos.app.product.application.out.ProductOptionPort;
import com.gw.gupos.api.pos.app.product.application.out.ProductPort;
import com.gw.guposcore.domain.product.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
class ProductService implements ProductUseCase {
    private final ProductPort productPort;
    private final ProductOptionPort productOptionPort;
    private final ProductCategoryPort productCategoryPort;

    @Override
    public Product createProduct(Product product) {
        return productPort.save(product);
    }

    @Override
    public Product createProduct(CreateProductRequest request) {

        Product buildProduct = Product.create(request.getProductNm(), "", "", request.getProductPrice(), request.getStockAt(), request.getStockCount());

        ProductCategory foundCategory = productCategoryPort.findById(request.getCategoryId());
        buildProduct.withCategory(foundCategory);

        for (Long optionGroupId : request.getOptionGroupIds()) {
            ProductOptionGroup foundOptionGroup = productOptionPort.findOptionGroupById(optionGroupId);
            buildProduct.addProductOption(ProductOptionRel.create(buildProduct, foundOptionGroup));
        }

        Product savedProduct = productPort.save(buildProduct);

        return savedProduct;
    }

    @Override
    public List<Product> getProductList() {
        return productPort.findList();
    }

    @Override
    public List<Product> getProductListWithOptions() {
        List<Product> foundProduct = productPort.findProductListWithOptions();
        for (Product product : foundProduct) {
            for (ProductOptionRel optionRel : product.getProductOptionRelList()) {
                for (ProductOption productOption : optionRel.getProductOptionGroup().getProductOptionList()) {
                    Long productOptionId = productOption.getProductOptionId();
                }
            }
        }
        return foundProduct;
    }

    @Override
    public Product getProductById(Long productId) {
        return productPort.findById(productId);
    }
}
