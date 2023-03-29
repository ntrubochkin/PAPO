package com.noname.books_exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noname.books_exchange.model.VerificationInfo;
import com.noname.books_exchange.service.UserService;
import com.noname.books_exchange.service.VerificationInfoService;
import com.noname.books_exchange.utils.PageAttributes;

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
    public String verify(Model model, @PathVariable(value = "verificationString") String randomStr)
    {
        VerificationInfo info = vService.findByGeneratedString(randomStr);
        boolean result = false;
        if(info != null) {
            userService.enableUser(info.getIdUser());
            vService.deleteRow(info);
            result = true;
        }
        model.addAttribute(PageAttributes.VERIFICATION_STATUS, result);
        return "verification";
    }
}