package com.example.spacebidder.controller;

import com.example.spacebidder.model.Product;
import com.example.spacebidder.model.Seller;
import com.example.spacebidder.repository.ProductRepository;
import com.example.spacebidder.repository.SellerRepository;
import com.example.spacebidder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController
{

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    @GetMapping("/")
    private String getEvaluationPage(Model model) {
        // Fetch distinct categories
        List<String> distinctCategories = productRepository.findDistinctCategories();

        model.addAttribute("distinctCategories", distinctCategories);
        return "evaluation";
    }


    @GetMapping("/automotive")
    private String getAllAutomotive(Model model) {
        List<Product> products1 = productRepository.findAllByProductCategory("Automotive");
        model.addAttribute("products", products1);
        model.addAttribute("category", "Automotive");
        return "products-evaluate";
    }

    @GetMapping("/computers")
    private String getAllComputers(Model model) {
        List<Product> products1 = productRepository.findAllByProductCategory("Computers");
        model.addAttribute("products", products1);
        model.addAttribute("category", "Computers");
        return "products-evaluate";
    }

    @GetMapping("/jewelry")
    private String getAllJewelry(Model model) {
        List<Product> products3 = productRepository.findAllByProductCategory("Jewelry"); // Fetch all products from the repository
        model.addAttribute("products", products3);
        model.addAttribute("category", "Jewelry");
        return "products-evaluate";
    }

    @GetMapping("/appliances")
    private String getAllAppliances(Model model) {
        List<Product> products4 = productRepository.findAllByProductCategory("Appliances"); // Fetch all products from the repository
        model.addAttribute("products", products4);
        model.addAttribute("category", "Appliances");
        return "products-evaluate";
    }

    @GetMapping("/real-estate")
    private String getAllRealEstate(Model model) {
        List<Product> products5 = productRepository.findAllByProductCategory("Real-Estate"); // Fetch all products from the repository
        model.addAttribute("products", products5);
        model.addAttribute("category", "Real-Estate");
        return "products-evaluate";
    }
    @GetMapping("/clothes")
    private String getAllClothes(Model model) {
        List<Product> products5 = productRepository.findAllByProductCategory("Clothes"); // Fetch all products from the repository
        model.addAttribute("products", products5);
        model.addAttribute("category", "Clothes");
        return "products-evaluate";
    }


    @PostMapping("/submit-evaluation/{productId}")
    private String submitEvaluation(@PathVariable long productId, @RequestParam int stars) {
        Product p = productRepository.findById(productId).get();
        p.setStars(0);
        productRepository.save(p);
        p.setStars(stars);
        productRepository.save(p);
        return "submit-evaluation-success";
    }


    @GetMapping("/seller")
    private String getSellerEvaluationPage(Model model) {
        // Fetch distinct categories
        List<Seller> s = sellerRepository.findAll();

        model.addAttribute("sellerCategories", s);
        return "seller-evaluation";
    }
    @PostMapping("/submit-evaluation-for-seller/{sellerId}")
    private String submitEvaluationForSeller(@PathVariable Long sellerId, @RequestParam int stars) {
        Seller existingSeller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));
        existingSeller.setStars(stars);
        sellerRepository.save(existingSeller);
        return "submit-evaluation-success1";
    }

}

