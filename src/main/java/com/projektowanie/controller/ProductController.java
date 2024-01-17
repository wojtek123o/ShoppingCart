package com.projektowanie.controller;

import com.projektowanie.model.Category;
import com.projektowanie.model.Product;
import com.projektowanie.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String addProductScreen(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("newProduct", new Product());
        return "addProductScreen";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/employees/mainScreen";
    }

}
