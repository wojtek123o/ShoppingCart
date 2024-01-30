package com.projektowanie.controller;

import com.projektowanie.model.*;
import com.projektowanie.service.OrderService;
import com.projektowanie.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/order/placeOrder")
    public String placeOrder(Model model) {
        model.addAttribute("customerOrder", new CustomerOrder());
        model.addAttribute("shipping", new Shipping());
        List<Discount> availableDiscounts = orderService.getAvailableDiscounts();
        model.addAttribute("availableDiscounts", availableDiscounts);
        return "order/addOrderScreen";
    }

    @PostMapping("/order/placeOrder")
    public String placeOrder(Model model, @ModelAttribute Shipping shipping, @ModelAttribute CustomerOrder customerOrder , RedirectAttributes redirectAttributes) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartWithProducts();
        orderService.placeOrder(customerOrder, shoppingCart, shipping);
        redirectAttributes.addAttribute("orderId", customerOrder.getOrderId());
        return "redirect:/order/orderComplete/{orderId}";
    }

    @GetMapping("/order/orderComplete/{orderId}")
    public String orderComplete(@PathVariable Long orderId, Model model) {
        CustomerOrder completedOrder = orderService.getOrderById(orderId);
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCartWithProducts();
        model.addAttribute("customerOrder", completedOrder);
        model.addAttribute("shoppingCart", shoppingCart);
        return "/order/orderSummaryScreen";
    }

    @GetMapping("/order/orderCompleted")
    public String finalComplete(){
        return "/order/orderCompleteScreen";
    }

}
