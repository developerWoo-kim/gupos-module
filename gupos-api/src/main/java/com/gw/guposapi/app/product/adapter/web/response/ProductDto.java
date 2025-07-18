package com.gw.guposapi.app.product.adapter.web.response;

import com.gw.guposcore.domain.product.Product;
import lombok.Data;

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
    }
}
