package com.gw.guposcore.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity(name = "tb_pd_product")
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productNm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    private String simpleDescription;
    private String description;
    private int productPrice;
    private String stockAt;
    private Integer stockCount;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOptionRel> productOptionRelList = new ArrayList<>();

    @Builder
    public Product(String productNm, String simpleDescription, String description, int productPrice, String stockAt, Integer stockCount) {
        this.productNm = productNm;
        this.simpleDescription = simpleDescription;
        this.description = description;
        this.productPrice = productPrice;
        this.stockCount = stockCount;
        this.stockAt = stockAt;
    }

    public static Product create(String productNm, String simpleDescription, String description, int productPrice, String stockAt, Integer stockCount) {
        return builder()
                .productNm(productNm)
                .simpleDescription(simpleDescription)
                .description(description)
                .productPrice(productPrice)
                .stockAt(stockAt)
                .stockCount(stockAt.equals("Y") ? stockCount : null)
                .build();
    }

    public void withCategory(ProductCategory productCategory) {
        this.category = productCategory;
    }

    public void addProductOption(ProductOptionRel productOptionRel) {
        productOptionRelList.add(productOptionRel);
    }
}
