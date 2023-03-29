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

    public void logout() {
        loggedIn = false;
        user = null;
        avatarBase64String = DEFAULT_BASE64_AVATAR;
    }

    public void login(User user) {
        login(user, user.getAvatar(), user.getAvatarType());
    }

    public void login(User user, byte[] avatar, String avatarType) {
        this.loggedIn = true;
        this.user = user;
        this.avatarBase64String = (avatar == null) ?
                                  DEFAULT_BASE64_AVATAR :
                                  GeneralUtils.imageToBase64String(avatar, avatarType);
    }

    public void setGeneralPageInfo(Model model) {
        model.addAttribute(PageAttributes.HOME_IS_ANON, loggedIn);
        model.addAttribute(PageAttributes.USER_AVATAR, avatarBase64String);
        if(loggedIn) {
            model.addAttribute(PageAttributes.USER_NAME, user.getUserName());
            model.addAttribute(PageAttributes.USER_RATING, user.getRating());
        }
    }

    public void setErrorMessage(Model model, String message) {
        model.addAttribute(PageAttributes.LOGIN_ERROR, true);
        model.addAttribute(PageAttributes.LOGIN_ERROR_MESSAGE, message);
    }
}