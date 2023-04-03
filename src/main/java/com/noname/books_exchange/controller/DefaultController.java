package com.noname.books_exchange.controller;

import com.noname.books_exchange.model.Author;
import com.noname.books_exchange.model.BookLiterary;
import com.noname.books_exchange.model.OfferList;
import com.noname.books_exchange.model.UserValueCategory;
import com.noname.books_exchange.utils.BlankExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noname.books_exchange.utils.ClientState;

@Controller
public class DefaultController {

    private final ClientState clientState;
    private final BlankExchange blankExchange;

    @Autowired
    public DefaultController(ClientState state, BlankExchange blankExchange) {
        clientState = state;
        this.blankExchange = blankExchange;
    }

    @RequestMapping("/")
    public String defaultPath() {
        //TODO Можно смотреть домашнюю страницу без входа?
        if(clientState.loggedIn) {
            return "redirect:home";
        }
        return "redirect:login";
    }

    //TODO может ему нужно будет выделить свой отдельный файлик
    @RequestMapping("/home")
    public String home(Model model) {
        clientState.setGeneralPageInfo(model);
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        if(clientState.loggedIn) {
            return "redirect:home";
        }
        return "login";
    }

    @RequestMapping("/registration")
    public String registration() {
        if(clientState.loggedIn) {
            return "redirect:home";
        }
        return "registration";
    }

    @RequestMapping("/go_trade")
    public String goTrade(Model model) {
        clientState.setGeneralPageInfo(model);
        //TODO: Венера.Как бы это переделать нормально...
        model.addAttribute("author", new Author());
        model.addAttribute("bookLiterary", new BookLiterary());
        model.addAttribute("userValueCategory", new UserValueCategory());
        blankExchange.setCategories(model);

        return "go_trade";
    }

    @RequestMapping("/my_trades")
    public String myTrades(Model model) {
        clientState.setGeneralPageInfo(model);
        if(!clientState.loggedIn) {
            return "redirect:login";
        }
        return "my_trades";
    }

    @RequestMapping("/aaq")
    public String aaq(Model model) {
        clientState.setGeneralPageInfo(model);
        return "aaq";
    }

    @RequestMapping("/go_get")
    public String goGet(Model model) {
        clientState.setGeneralPageInfo(model);
        //TODO: Венера.Как бы это переделать нормально...
        blankExchange.setCategories(model);
        return "go_get";
    }

    @RequestMapping("/go_address")
    public String goAddress(Model model) {
        clientState.setGeneralPageInfo(model);
        return "go_address";
    }
}
