package com.gw.gupos.api.pos.app.product.adapter.web;

import com.gw.gupos.api.pos.app.product.adapter.web.request.UpdateCategoryProductRequest;
import com.gw.gupos.api.pos.app.product.adapter.web.request.UpdateCategorySortOrderRequest;
import com.gw.gupos.api.pos.app.product.adapter.web.response.ProductCategoryDto;
import com.gw.gupos.api.pos.app.product.adapter.web.response.ProductDto;
import com.gw.gupos.api.pos.app.product.application.in.ProductCategoryUseCase;
import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductCategoryApiController {
    private final ProductCategoryUseCase productCategoryUseCase;

    @GetMapping("/api/v1/pos/product/categories/list")
    public List<ProductCategoryDto> getCategoryList() {
        List<ProductCategory> categoryList = productCategoryUseCase.getCategoryListWithProducts();
        return ProductCategoryDto.createList(categoryList);
    }

    @PostMapping("/api/v1/pos/product/categories/{categoryNm}")
    public ProductCategoryDto createCategory(@PathVariable("categoryNm") String categoryNm) {
        ProductCategory category = productCategoryUseCase.createCategory(categoryNm);
        return new ProductCategoryDto(category);
    }

    @PutMapping("/api/v1/pos/product/categories/category-name")
    public void updateCategoryName(@RequestBody UpdateCategoryProductRequest request) {
        productCategoryUseCase.updateCategory(request);
    }

    @PutMapping("/api/v1/pos/product/categories/sort-order")
    public void updateCategorySortOrder(@RequestBody UpdateCategorySortOrderRequest request) {
        productCategoryUseCase.updateSortOrder(request);
    }

    @DeleteMapping("/api/v1/pos/product/categories/{categoryId}")
    public void updateCategoryName(@PathVariable("categoryId") Long categoryId) {
        productCategoryUseCase.deleteCategory(categoryId);
    }
}
