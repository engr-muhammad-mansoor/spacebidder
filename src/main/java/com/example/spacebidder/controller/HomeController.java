package com.example.spacebidder.controller;

import com.example.spacebidder.model.Seller;
import com.example.spacebidder.repository.SellerRepository;
import com.example.spacebidder.service.CartService;
import com.example.spacebidder.ProductOperation;
import com.example.spacebidder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final CartService cartService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public HomeController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("items", cartService.getAllItems());

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            // User is authenticated
            String name = authentication.getName();
            model.addAttribute("name", name);
        } else {
            // User is not logged in
            model.addAttribute("name", null);
        }

        List<Seller> best = sellerRepository.findAll();
        model.addAttribute("best", best);

        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId, Model model) {
        cartService.itemOperation(itemId, ProductOperation.INCREASE);
        model.addAttribute("items", cartService.getAllItems());
        return "home";
    }
}
