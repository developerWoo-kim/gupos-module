package com.gw.guposcore.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "tb_pd_product_category")
public class ProductCategory {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    private String categoryNm;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> productList = new ArrayList<>();

    private int sortOrder;

    @Builder
    public ProductCategory(String categoryNm, int sortOrder) {
        this.categoryNm = categoryNm;
        this.sortOrder = sortOrder;
    }

    public static ProductCategory create(String categoryNm, int sortOrder) {
        return builder()
                .categoryNm(categoryNm)
                .sortOrder(sortOrder)
                .build();
    }

    public void updateCategoryNm(String categoryNm) {
        this.categoryNm = categoryNm;
    }
}
