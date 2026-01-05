package com.example.spacebidder.controller;

import com.example.spacebidder.ProductOperation;
import com.example.spacebidder.dto.OrderDto;
import com.example.spacebidder.service.CartService;
import com.example.spacebidder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;

    @Autowired
    public OrderController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String showCart() {
        return "cartView";
    }

    @PostMapping("/increase/{productId}")
    public String increaseItem(@PathVariable("productId") Long productId) {
        cartService.itemOperation(productId, ProductOperation.INCREASE);
        return "cartView";
    }

    @PostMapping("/decrease/{itemId}")
    public String decreaseItem(@PathVariable("itemId") Long itemId) {
        cartService.itemOperation(itemId, ProductOperation.DECREASE);
        return "cartView";
    }

    @GetMapping("/remove/{itemId}")
    public String removeItemsFromCart(@PathVariable("itemId") Long itemId) {
        cartService.itemOperation(itemId, ProductOperation.REMOVE);
        return "cartView";
    }

    @GetMapping("/summary")
    public String showSummary() {
        return "summary";
    }

    @PostMapping("/saveorder")
    public String saveOrder(Model model,OrderDto orderDto) {
        long orderId = orderService.saveOrder(orderDto);
        model.addAttribute("orderId",orderId);
        return "order-place-success";
    }
}
