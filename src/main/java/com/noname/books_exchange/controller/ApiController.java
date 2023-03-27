package com.noname.books_exchange.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noname.books_exchange.service.UserService;
import com.noname.books_exchange.utils.GeneralUtils;

@Controller
@RequestMapping("/api/v1")
public class ApiController {
    private final UserService userService;

    @Autowired
    public ApiController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/getRandomNickname")
    @ResponseBody
    public String sendRandomNickname() {
        String result = "";
        do {
            String randomNickname = 
                GeneralUtils.getHtmlPage("https://names.drycodes.com/1");
            randomNickname = 
                randomNickname.substring(2, randomNickname.length() - 2)
                .replace("_", " ");
            try {
                String response =
                    GeneralUtils.getHtmlPage("https://script.google.com/macros/s/AKfycbxTomIfHhsthhGahBIbibH3gX2qIbo5C-J--dff5fSI8IZifd5oJDTCHJEu50Tp2peSFQ/exec" +
                    "?to_translate=" +
                    URLEncoder.encode(randomNickname, StandardCharsets.UTF_8.toString()));
                result = response;
            } catch (UnsupportedEncodingException e) {
                // TODO
                e.printStackTrace();
            }
        } while(!userService.isUserNameUnique(result) && result.length() > 20);
        return result;
    }
}