package com.example.spacebidder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/extras")
public class ContactUsController {

    @GetMapping("/kontakt")
    public String contactUs(){
        return "kontakt";
    }
    @GetMapping("/regulamin")
    public String rules(){
        return "regulamin";
    }
}
