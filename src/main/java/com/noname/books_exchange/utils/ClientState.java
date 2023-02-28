package com.noname.books_exchange.utils;

import org.springframework.stereotype.Component;

@Component
public class ClientState {
    public boolean loggedIn = false;
    //TODO При ошибке логина или регистрации выводить ошибку
    public String messageToUser = "";
}
