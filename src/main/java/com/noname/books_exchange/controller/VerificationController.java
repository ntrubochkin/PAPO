package com.noname.books_exchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/verification")
public class VerificationController {
    
    @GetMapping("/{verificationString}")
    public String verify(@PathVariable(value = "verificationString") String randomStr) {
        System.out.println(randomStr);
        return "verification";
    }
}
