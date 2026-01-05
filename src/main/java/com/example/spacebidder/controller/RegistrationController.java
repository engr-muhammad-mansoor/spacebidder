package com.example.spacebidder.controller;

import com.example.spacebidder.model.User;
import com.example.spacebidder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/new/")
    public String processRegistration(@ModelAttribute("user") User user, Model model) {
        boolean check = userService.CheckUserExist(user.getClientEmail());
        if (!check) {
            userService.saveUser(user);
            return "registration-success";
        } else {
            model.addAttribute("userExists", true);
            return "registration-failure";
        }
    }

}
