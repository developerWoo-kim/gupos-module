package com.gw.guposcore.domain.product;

import jakarta.persistence.*;
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

//    @Enumerated(EnumType.STRING)
//    private ProductStatus productStatus;

    private int productPrice;
    private int stockCount;
}
