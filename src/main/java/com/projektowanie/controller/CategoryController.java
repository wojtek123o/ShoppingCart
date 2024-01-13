package com.projektowanie.controller;

import com.projektowanie.service.CategoryService;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
