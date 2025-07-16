package com.gw.guposcore.domain.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    private String productId;
    private String productNm;
    private String simpleDescription;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private int productPrice;
    private int stockCount;
}
