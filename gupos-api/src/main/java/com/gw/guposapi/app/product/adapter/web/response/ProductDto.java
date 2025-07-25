package com.gw.guposapi.app.product.adapter.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductOption;
import com.gw.guposcore.domain.product.ProductOptionGroup;
import com.gw.guposcore.domain.product.ProductOptionRel;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDto {
    private Long productId;
    private String productNm;
    private String simpleDescription;
    private String description;
    private int productPrice;
    private String stockAt;
    private Integer stockCount;

    private List<ProductOptionGroupDto> optionGroupList = new ArrayList<>();

    public static List<ProductDto> createList(List<Product> products) {
        List<ProductDto> list = new ArrayList<>();
        for (Product product : products) {
            list.add(new ProductDto(product));
        }
        return list;
    }

    public ProductDto(Product product) {
        this.productId = product.getProductId();
        this.productNm = product.getProductNm();
        this.simpleDescription = product.getSimpleDescription();
        this.description = product.getDescription();
        this.productPrice = product.getProductPrice();
        this.stockAt = product.getStockAt();
        this.stockCount = product.getStockCount();

        if(Hibernate.isInitialized(product.getProductOptionRelList()) && product.getProductOptionRelList() != null) {
            for (ProductOptionRel productOptionRel : product.getProductOptionRelList()) {
                optionGroupList.add(new ProductOptionGroupDto(productOptionRel.getProductOptionGroup()));
            }
        }
    }
}
