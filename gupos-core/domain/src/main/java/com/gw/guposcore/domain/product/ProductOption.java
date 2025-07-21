package com.gw.guposcore.domain.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "tb_pd_product_option")
public class ProductOption {
    @Id
    @Column(name = "product_option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_group_id")
    private ProductOptionGroup productOptionGroup;

    private String productOptionNm;
    private int optionPrice;
    private int sortOrder;

    @Builder()
    public ProductOption(ProductOptionGroup productOptionGroup, String productOptionNm, int optionPrice, int sortOrder) {
        this.productOptionGroup = productOptionGroup;
        this.productOptionNm = productOptionNm;
        this.optionPrice = optionPrice;
        this.sortOrder = sortOrder;
    }

    public static ProductOption create(String productOptionNm, int optionPrice, int sortOrder) {
        return builder()
                .productOptionNm(productOptionNm)
                .optionPrice(optionPrice)
                .sortOrder(sortOrder)
                .build();
    }

    public void withProductOptionGroup(ProductOptionGroup productOptionGroup) {
        this.productOptionGroup = productOptionGroup;
    }
}
