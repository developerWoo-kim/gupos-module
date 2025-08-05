package com.gw.gupos.api.order.app.order.adapter.web;

import com.gw.guposcore.domain.product.Product;
import com.gw.guposcore.domain.product.ProductCategory;
import com.gw.gupos.api.order.app.product.adapter.web.response.ProductDto;
import com.gw.gupos.api.order.app.product.application.in.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.gw.gupos.api.order.app.product.adapter.web.response.ProductCategoryDto.createList;


@Controller
@RequestMapping("/table")
@RequiredArgsConstructor
public class OrderController {
    private final ProductUseCase productUseCase;

    @GetMapping("/{tableId}/menu/{menuId}")
    public String getMenuDetail(@PathVariable("tableId") String tableId, @PathVariable("menuId") Long menuId, Model model) {
        Product product = productUseCase.getProduct(menuId);
        model.addAttribute("product", new ProductDto(product));
        return "app/order/order_table_menu";
    }

    @RequestMapping("/{tableId}")
    public String getOrderTable(@PathVariable("tableId") String tableId, Model model) {
        List<ProductCategory> categoryList = productUseCase.getProductCategoriesWithProducts();
        model.addAttribute("categoryList", createList(categoryList));
        return "app/order/order_table";
    }
}
