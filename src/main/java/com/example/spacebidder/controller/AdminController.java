package com.example.spacebidder.controller;

import com.example.spacebidder.model.Product;
import com.example.spacebidder.model.User;
import com.example.spacebidder.model.order.Order;
import com.example.spacebidder.repository.ProductRepository;
import com.example.spacebidder.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public AdminController(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/")
    private String adminPage(Model model) {
        model.addAttribute("product", new Product());
        return "admin";
    }

    @PostMapping("/add-item")
    public String addItem(@ModelAttribute("product") Product product, Model model) {
        productRepository.save(product);
        model.addAttribute("successMessage", "Product added successfully!");
        return "admin";
    }


    @GetMapping("/showorders")
    @ResponseBody
    public List<Order> showOrders() {
        return orderRepository.findAll();
    }
}
