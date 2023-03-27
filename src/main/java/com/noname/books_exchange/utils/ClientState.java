package com.noname.books_exchange.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.noname.books_exchange.model.User;

@Component
public class ClientState {
    private static String DEFAULT_BASE64_AVATAR;

    public boolean loggedIn = false;
    public User user = null;
    public String avatarBase64String = "";
    //TODO При ошибке логина или регистрации выводить ошибку
    public String messageToUser = "";

    static {
        File defaultFile = GeneralUtils.getResourceFile("default.jpg");
        try {
            byte[] bytes = Files.readAllBytes(defaultFile.toPath());
            DEFAULT_BASE64_AVATAR = GeneralUtils.imageToBase64String(bytes, "image/jpg");
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }

    public void login(User user) {
        login(user, user.getAvatar(), user.getAvatarType());
    }

    public void login(User user, byte[] avatar, String avatarType) {
        this.loggedIn = true;
        this.user = user;
        if(avatar == null) {
            this.avatarBase64String = DEFAULT_BASE64_AVATAR;
        } else {
            this.avatarBase64String = GeneralUtils.imageToBase64String(avatar, avatarType);
        }
    }

    public void setPageInfo(Model model) {
        model.addAttribute(PageAttributes.HOME_IS_ANON, loggedIn);
        model.addAttribute(PageAttributes.USER_AVATAR, avatarBase64String);
        if(loggedIn) {
            model.addAttribute(PageAttributes.USER_NAME, user.getUserName());
            model.addAttribute(PageAttributes.USER_RATING, user.getRating());
        }
    }
}