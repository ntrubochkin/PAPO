package com.noname.books_exchange.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noname.books_exchange.model.User;
import com.noname.books_exchange.service.UserService;
import com.noname.books_exchange.service.VerificationInfoService;
import com.noname.books_exchange.utils.ClientState;
import com.noname.books_exchange.utils.GeneralUtils;

//https://dev.vk.com/reference/versions
@Controller
@RequestMapping("/oauth/vk")
public class VKontakteController {
    private final UserService userService;
    private final VerificationInfoService vService;
    private final ClientState clientState;

    @Autowired
    public VKontakteController(UserService userService, VerificationInfoService vService, ClientState clientState) {
        this.userService = userService;
        this.vService = vService;
        this.clientState = clientState;
    }

    @GetMapping("/")
    public String vkAuth() {
        return "vk-auth.html";
    }

    @PostMapping("/eat-data")
    public String vkLogin(@RequestParam(value = "email") String email,
                          @RequestParam(value = "firstName") String firstName,
                          @RequestParam(value = "lastName") String lastName,
                          @RequestParam(value = "hasAvatar") String hasAvatarStr,
                          @RequestParam(value = "avatarURL") String avatarURL,
                          @RequestParam(value = "userName") String userName)
    {
        byte[] avatar = null;
        boolean hasAvatar = hasAvatarStr.equals("1");
        if(hasAvatar) {
            int length = 1024 * 1024 * 5;
            try {
                URL url = new URL(avatarURL);
                int size = GeneralUtils.getImageSizeFromURL(url); //Bruh
                if(size == -1) {
                    size = length;
                }
                InputStream stream = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[size];
                int bytesRead;
                while((bytesRead = stream.read(buffer, 0, size)) != -1) {
                    byteStream.write(buffer, 0, bytesRead);
                }
                stream.close();
                byteStream.close();
                avatar = byteStream.toByteArray();
            } catch (MalformedURLException murle) {
                //TODO
            } catch (IOException ioe) {
                //TODO
            }
        }
        Pair<User, Boolean> vkResult =
            userService.handleVKontakteAuth(firstName, lastName, email, userName, avatar);
        User user = vkResult.getFirst();
        if(vkResult.getSecond()) {
            vService.sendVerificationEmail(user.getIdUser(), email, userName);
        }
        //TODO
        clientState.loggedIn = true;
        return "redirect:home";
    }
}