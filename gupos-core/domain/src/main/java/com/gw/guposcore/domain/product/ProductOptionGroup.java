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
@Entity(name = "tb_pd_product_option_group")
public class ProductOptionGroup {
    @Id
    @Column(name = "product_option_group_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productOptionGroupId;

    @JsonIgnore
    @OneToMany(mappedBy = "productOptionGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> productOptionList = new ArrayList<>();

    private String productOptionGroupNm;
    private String requiredAt;
    private int maxSelectionCount;
    private int sortOrder;

    @Builder
    public ProductOptionGroup(String productOptionGroupNm, String requiredAt, int maxSelectionCount, int sortOrder) {
        this.productOptionGroupNm = productOptionGroupNm;
        this.requiredAt = requiredAt;
        this.maxSelectionCount = maxSelectionCount;
        this.sortOrder = sortOrder;
    }

    public static ProductOptionGroup create(String productOptionGroupNm, String requiredAt, int maxSelectionCount, int sortOrder) {
        return builder()
                .productOptionGroupNm(productOptionGroupNm)
                .requiredAt(requiredAt)
                .maxSelectionCount(maxSelectionCount)
                .sortOrder(sortOrder)
                .build();
    }

    public void addProductOption(ProductOption productOption) {
        productOption.withProductOptionGroup(this);
        productOptionList.add(productOption);
    }

}
