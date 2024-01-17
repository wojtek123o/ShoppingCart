package com.projektowanie.controller;

import com.projektowanie.model.Category;
import com.projektowanie.model.Product;
import com.projektowanie.service.CategoryService;
import com.projektowanie.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;


    @GetMapping("/add")
    public String addProductScreen(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("category", new Category());
        model.addAttribute("newProduct", new Product());
        return "addProductScreen";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/employees/mainScreen";
    }

    @GetMapping("/edit")
    public String editProductsScreen(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "editProductsScreen";
    }

    @GetMapping("/edit/{id}")
    public String editProductScreen(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "editProductScreen";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product updatedProduct) {
        productService.updateProduct(updatedProduct);
        return "redirect:/products/edit";
    }

    @GetMapping("/delete")
    public String deleteProductScreen(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "deleteProductScreen";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productService.deleteProduct(id);
        return "redirect:/products/delete";
    }

}
