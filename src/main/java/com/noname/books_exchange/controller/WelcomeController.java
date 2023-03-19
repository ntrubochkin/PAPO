package com.noname.books_exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.noname.books_exchange.service.UserService;
import com.noname.books_exchange.utils.ClientState;
import com.noname.books_exchange.utils.RegexUtils;

@Controller
public class WelcomeController {

    private final ClientState clientState;
    private final UserService userService;

    @Autowired
    public WelcomeController(ClientState clientState, UserService userService) {
        this.clientState = clientState;
        this.userService = userService;
    }

    //TODO
    @PostMapping("registration-attempt")
    public String tryRegister(@RequestParam(value = "firstname",          required = true)  String firstname,
                              @RequestParam(value = "lastname",           required = true)  String lastname,
                              @RequestParam(value = "surname",            required = false) String surname,
                              @RequestParam(value = "email",              required = true)  String email,
                              @RequestParam(value = "userName",           required = true)  String userName,
                              @RequestParam(value = "password",           required = true)  String password,
                              @RequestParam(value = "passwordConfirmStr", required = true)  String pwdConfirmStr,
                              @RequestParam(value = "avatarFile",         required = false) MultipartFile avatarFile,
                              @RequestParam(value = "city",               required = false) String city,
                              @RequestParam(value = "street",             required = false) String street,
                              @RequestParam(value = "building",           required = false) String buildingNumber,
                              @RequestParam(value = "home",               required = false) String homeNumber,
                              @RequestParam(value = "apartment",          required = false) String apartmentNumber,
                              @RequestParam(value = "index",              required = false) Integer index)
    {
        System.out.println(firstname);
        System.out.println(lastname);
        System.out.println(surname);
        System.out.println(email);
        System.out.println(RegexUtils.validateEmail(email));
        System.out.println(userName);
        System.out.println(password);
        System.out.println(RegexUtils.validatePassword(password));
        System.out.println(pwdConfirmStr);
        if(avatarFile != null)
        {
            System.out.println(avatarFile.getOriginalFilename());
        }
        System.out.println(city);
        System.out.println(street);
        System.out.println(buildingNumber);
        System.out.println(homeNumber);
        System.out.println(apartmentNumber);
        System.out.println(index);
        return "redirect:home";
    }

    //TODO
    @PostMapping("/login-attempt")
    public String tryLogin(@RequestParam(value = "login",       required = true) String login,
                           @RequestParam(value = "password",    required = true) String password)
    {
        System.out.println(login);
        System.out.println(password);
        boolean result = true;
        clientState.loggedIn = result; 
        return "redirect:home";
    }
}
