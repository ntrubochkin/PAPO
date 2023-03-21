package com.noname.books_exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noname.books_exchange.model.VerificationInfo;
import com.noname.books_exchange.service.UserService;
import com.noname.books_exchange.service.VerificationInfoService;

@Controller
@RequestMapping("/verification")
public class VerificationController {

    private final VerificationInfoService vService;
    private final UserService userService;

    @Autowired
    public VerificationController(VerificationInfoService vService, UserService userService) {
        this.vService = vService;
        this.userService = userService;
    }
    
    @GetMapping("/{verificationString}")
    public String verify(@PathVariable(value = "verificationString") String randomStr) {
        VerificationInfo info = vService.findByGeneratedString(randomStr);
        if(info != null) {
            userService.enableUser(info.getIdUser());
            vService.deleteRow(info);
            //TODO сообщение об успешной верификации
        }
        //TODO сообщение об ошибке
        return "verification";
    }
}