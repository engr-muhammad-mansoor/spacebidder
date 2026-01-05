package com.example.spacebidder.controller;

import com.example.spacebidder.model.User;
import com.example.spacebidder.repository.UserRepository;
import com.example.spacebidder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/edit-user")
    public String editUserPage(Model model, Authentication authentication) {
        // Get the authenticated user's email
        String userEmail = authentication.getName();

        // Populate the User object with other details (you might have a service method to fetch user details)
        User user = userRepository.findUserByClientEmail(userEmail);

        // Add the User object to the model
        model.addAttribute("user", user);

        return "edit-user"; // Assuming your Thymeleaf template is named "edit-user.html"
    }


    @PostMapping("/edit-user/")
    public String editUser(@ModelAttribute("user") User user) {
        // Set the client_id in the User object before saving (assuming you want to update the user with the provided ID)
        userService.updateUser(user);
        return "user-updated";
    }

    @GetMapping("/reset-password")
    public String resetPassword(Model model) {
        model.addAttribute("user", new User());
        return "reset-password";
    }

    @PostMapping("/reset-password/")
    public String processResetPassword(@ModelAttribute("user") User user, Model model) {
        // Check if the provided email exists in the database (you need to implement this logic)
        boolean emailExists = (userRepository.existsByClientEmailAndClientLogin(user.getClientEmail(),user.getClientLogin()));

        // Add a flag to the model to conditionally render new password fields
        model.addAttribute("showPasswordFields", emailExists);

        // Pass the user object back to the form
        model.addAttribute("user", user);

        return "reset-password";
    }

    @PostMapping("/update-password")
    public String updatePassword(@ModelAttribute("user") User user, Model model) {
        try {
            User u = userRepository.findUserByClientEmail(user.getClientEmail());
            u.setClientPass(user.getClientPass()); // Use the newPassword field
            userRepository.save(u);
            // Redirect to a success page or login page
            return "reset-password-success";
        } catch (Exception e) {
            model.addAttribute("passwordUpdateError", true);
            return "reset-password";
        }
    }
}
