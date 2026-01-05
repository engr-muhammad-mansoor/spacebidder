package com.example.spacebidder.controller;

import com.example.spacebidder.model.Product;
import com.example.spacebidder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/automotive")
    private String getAllAutomotive(Model model) {
        List<Product> products1 = productRepository.findAllByProductCategory("Automotive");
        model.addAttribute("products", products1);
        model.addAttribute("category", "Automotive");
        return "products";
    }

    @GetMapping("/computers")
    private String getAllComputers(Model model) {
        List<Product> products1 = productRepository.findAllByProductCategory("Computers");
        model.addAttribute("products", products1);
        model.addAttribute("category", "Computers");
        return "products";
    }

    @GetMapping("/jewelry")
    private String getAllJewelry(Model model) {
        List<Product> products3 = productRepository.findAllByProductCategory("Jewelry"); // Fetch all products from the repository
        model.addAttribute("products", products3);
        model.addAttribute("category", "Jewelry");
        return "products";
    }

    @GetMapping("/appliances")
    private String getAllAppliances(Model model) {
        List<Product> products4 = productRepository.findAllByProductCategory("Appliances"); // Fetch all products from the repository
        model.addAttribute("products", products4);
        model.addAttribute("category", "Appliances");
        return "products";
    }

    @GetMapping("/real-estate")
    private String getAllRealEstate(Model model) {
        List<Product> products5 = productRepository.findAllByProductCategory("Real-Estate"); // Fetch all products from the repository
        model.addAttribute("products", products5);
        model.addAttribute("category", "Real-Estate");
        return "products";
    }
    @GetMapping("/clothing")
    private String getAllClothes(Model model) {
        List<Product> products5 = productRepository.findAllByProductCategory("Clothes"); // Fetch all products from the repository
        model.addAttribute("products", products5);
        model.addAttribute("category", "Clothes");
        return "products";
    }

}
