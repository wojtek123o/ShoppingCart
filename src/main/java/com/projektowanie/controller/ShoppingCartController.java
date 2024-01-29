package com.projektowanie.controller;

import com.projektowanie.model.ShoppingCart;
import com.projektowanie.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/shoppingCart/")
    public String shoppingCart(Model model) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartWithProducts();
        model.addAttribute("shoppingCart", shoppingCart);

        return "shoppingCart/productsInCartScreen";
    }

    @GetMapping("/shoppingCart/seed")
    public String seed() {
        shoppingCartService.seedShoppingCart();
        return "redirect:/shoppingCart/";
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public String removeProduct(@PathVariable Long productId) {
        shoppingCartService.removeProductFromCart(productId);
        return "redirect:/shoppingCart/";
    }

    @GetMapping("/shoppingCart/increaseQuantity/{productId}")
    public String increaseQuantity(@PathVariable Long productId) {
        shoppingCartService.increaseProductQuantity(productId);
        return "redirect:/shoppingCart/";
    }

    @GetMapping("/shoppingCart/decreaseQuantity/{productId}")
    public String decreaseQuantity(@PathVariable Long productId) {
        shoppingCartService.decreaseProductQuantity(productId);
        return "redirect:/shoppingCart/";
    }
}
