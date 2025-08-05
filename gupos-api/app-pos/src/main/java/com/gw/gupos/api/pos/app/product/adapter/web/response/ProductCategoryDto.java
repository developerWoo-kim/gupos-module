package com.gw.gupos.api.pos.app.product.adapter.web.response;

import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;
import lombok.Data;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductCategoryDto {
    private Long categoryId;
    private String categoryNm;
    private int sortOrder;

    private List<ProductDto> productList = new ArrayList<>();

    public static List<ProductCategoryDto> createList(List<ProductCategory> categoryList) {
        List<ProductCategoryDto> productCategoryDtoList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            productCategoryDtoList.add(new ProductCategoryDto(category));
        }
        return productCategoryDtoList;
    }

    public ProductCategoryDto(ProductCategory productCategory) {
        this.categoryId = productCategory.getCategoryId();
        this.categoryNm = productCategory.getCategoryNm();
        this.sortOrder = productCategory.getSortOrder();

        if(Hibernate.isInitialized(productCategory.getProductList()) && productCategory.getProductList() != null) {
            for (Product product : productCategory.getProductList()) {
                this.productList.add(new ProductDto(product));
            }
        }


    }
}
