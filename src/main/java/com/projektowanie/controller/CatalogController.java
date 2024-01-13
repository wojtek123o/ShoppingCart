package com.projektowanie.controller;

import com.projektowanie.service.CatalogService;
import org.springframework.stereotype.Controller;

@Controller
public class CatalogController {
    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }
}
