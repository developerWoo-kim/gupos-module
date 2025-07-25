package com.gw.guposcore.domain.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "tb_pd_product_option_rel")
public class ProductOptionRel {
    @Id
    @Column(name = "product_option_rel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOptionRelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_group_id")
    private ProductOptionGroup productOptionGroup;

    @Builder
    private ProductOptionRel(Product product, ProductOptionGroup productOptionGroup) {
        this.product = product;
        this.productOptionGroup = productOptionGroup;
    }

    public static ProductOptionRel create(Product product, ProductOptionGroup productOptionGroup) {
        return new ProductOptionRel(product, productOptionGroup);
    }
}
