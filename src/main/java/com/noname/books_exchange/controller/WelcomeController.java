package com.noname.books_exchange.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.noname.books_exchange.model.User;
import com.noname.books_exchange.service.UserAddressService;
import com.noname.books_exchange.service.UserService;
import com.noname.books_exchange.service.VerificationInfoService;
import com.noname.books_exchange.utils.ClientState;
import com.noname.books_exchange.utils.RegexUtils;

@Controller
public class WelcomeController {

    private final ClientState clientState;
    private final UserService userService;
    private final UserAddressService addressService;
    private final VerificationInfoService verificationService;

    @Autowired
    public WelcomeController(ClientState clientState,
                             UserService userService,
                             UserAddressService addressService,
                             VerificationInfoService vService)
    {
        this.clientState = clientState;
        this.userService = userService;
        this.addressService = addressService;
        verificationService = vService;
    }

    @PostMapping("registration-attempt")
    public String tryRegister(Model model,
                              @RequestParam(value = "firstname",          required = true)  String firstname,
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
        if(!userService.isUserNameUnique(userName)) {
            clientState.setErrorMessage(model, "Имя пользователя не уникально!");
            return "registration";
        }
        boolean pwdCheckResult = RegexUtils.validatePassword(password);
        if(!pwdCheckResult) {
            clientState.setErrorMessage(model, "Пароль не надёжный!");
            return "registration";
        }
        if(!password.equals(pwdConfirmStr)) {
            clientState.setErrorMessage(model, "Поля \'Пароль\' и \'Подтверждение пароля\' не совпадают!");
            return "registration";
        }
        if(!RegexUtils.validateEmail(email)) {
            clientState.setErrorMessage(model, "Почта неправильного формата!");
            return "registration";
        }
        byte[] avatar = null;
        String avatarType = "";
        if(!avatarFile.isEmpty()) {
            try {
                avatar = avatarFile.getBytes();
                avatarType = avatarFile.getContentType();
            } catch (IOException ioe) {
                //TODO
            }
        }
        User user = userService.createUser(firstname, lastname, surname, email, userName, password, avatar, avatarType);
        Integer id = user.getIdUser();
        if(!city.isEmpty()) {
            addressService.createAddress(id, city, street, buildingNumber, homeNumber, apartmentNumber, index);
        }
        boolean emailSent = verificationService.sendVerificationEmail(id, email, userName);
        if(emailSent) {

        } else {

        }
        clientState.login(user, avatar, avatarType);
        return "redirect:login";
    }

    @PostMapping("/login-attempt")
    public String tryLogin(Model model,
                           @RequestParam(value = "login",       required = true) String login,
                           @RequestParam(value = "password",    required = true) String password)
    {
        User user = userService.findUserByLoginData(login, password);
        if(user != null) {
            clientState.login(user);
            return "redirect:home";
        }
        //TODO сообщение об ошибке
        return "login";
    }
}