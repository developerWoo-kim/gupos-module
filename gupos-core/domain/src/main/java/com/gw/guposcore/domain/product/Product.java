package com.gw.guposcore.domain.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity(name = "tb_pd_product")
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productNm;
    private String simpleDescription;
    private String description;
    private int productPrice;
    private String stockAt;
    private Integer stockCount;


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
                .stockCount(stockCount)
                .build();
    }
}
