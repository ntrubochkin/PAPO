package com.noname.books_exchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noname.books_exchange.utils.ClientState;

@Controller
public class WelcomeController {

    private final ClientState clientState;

    public WelcomeController(ClientState state) {
        clientState = state;
    }

    //TODO
    @PostMapping("registration-attempt")
    public String tryRegister(@RequestParam(value = "userName") String userName,
                              @RequestParam(value = "email") String email,
                              @RequestParam(value = "password") String password)
    {
        System.out.println(userName);
        System.out.println(email);
        System.out.println(password);
        return "redirect:home";
    }

    //TODO
    @PostMapping("/login-attempt")
    public String tryLogin(@RequestParam(value = "login") String login,
                           @RequestParam(value = "password") String password)
    {
        System.out.println(login);
        System.out.println(password);
        boolean result = true;
        clientState.loggedIn = result; 
        return "redirect:home";
    }
}
