package com.noname.books_exchange.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noname.books_exchange.model.User;
import com.noname.books_exchange.service.UserService;
import com.noname.books_exchange.service.VerificationInfoService;
import com.noname.books_exchange.utils.ClientState;
import com.noname.books_exchange.utils.GeneralUtils;
import com.noname.books_exchange.utils.Triplet;

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
    @ResponseBody
    public HashMap<String, String> vkLogin(@RequestParam(value = "email", required = true) String email,
                                           @RequestParam(value = "firstName", required = true) String firstName,
                                           @RequestParam(value = "lastName", required = true) String lastName,
                                           @RequestParam(value = "hasAvatar", required = true) String hasAvatarStr,
                                           @RequestParam(value = "avatarURL", required = true) String avatarURL,
                                           @RequestParam(value = "userName", required = true) String userName)
    {
        byte[] avatar = null;
        String avatarType = "";
        boolean hasAvatar = hasAvatarStr.equals("1");
        HashMap<String, String> response = new HashMap<String, String>();
        if(hasAvatar) {
            try {
                URL url = new URL(avatarURL);
                Pair<Integer, String> imgInfo = GeneralUtils.getVKAvatarInfo(url);
                int size = imgInfo.getFirst();
                avatarType = imgInfo.getSecond();
                if(size == -1) {
                    size = GeneralUtils.MAX_FILE_UPLOAD_SIZE;
                    avatarType = ""; //TODO !!!
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
        Triplet<User, Boolean, String> vkResult =
            userService.handleVKontakteAuth(firstName, lastName, email, userName, avatar, avatarType);
        User user = vkResult.first;
        if(vkResult.second) {
            vService.sendVKInfo(email, firstName, lastName, userName, vkResult.third);
            vService.sendVerificationEmail(user.getIdUser(), email, userName);
            response.put("status", "created");
        } else {
            response.put("status", "exists");
        }
        clientState.login(user);
        return response;
    }
}