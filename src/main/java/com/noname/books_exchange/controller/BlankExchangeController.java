package com.noname.books_exchange.controller;

import com.noname.books_exchange.model.*;
import com.noname.books_exchange.service.BlankExchangeSevice;
import com.noname.books_exchange.utils.ClientState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlankExchangeController {

    List<UserValueCategory> userValueCategoryList = null;

    private final BlankExchangeSevice blankExchangeSevice;
    private final ClientState clientState;

    @Autowired
    public BlankExchangeController(BlankExchangeSevice blankExchangeSevice, ClientState clientState) {
        this.blankExchangeSevice = blankExchangeSevice;
        this.clientState = clientState;
    }

    //TODO: Добавление книги...
    @PostMapping("/go_trade")
    public String wantBookExchange(Model model,
                                   @ModelAttribute("author") Author author,
                                   @ModelAttribute("bookLiterary") BookLiterary bookLiterary,
                                   @RequestParam(value = "source", required = false) int[] source,
                                   @RequestParam(value = "isbn")  String isbn,
                                   @RequestParam(value = "year")  String year){

        if (!author.getFirstName().isEmpty() ||
                !author.getLastName().isEmpty() ||
                !bookLiterary.getBookName().isEmpty() ||
                !isbn.isEmpty() ||
                !year.isEmpty() ||
                source != null) {
            Author checkAuthor = blankExchangeSevice.getAuthorByName(author.getFirstName(), author.getLastName());
            if(checkAuthor == null) {
                //checkAuthor = blankExchangeSevice.createAuthor(author.getFirstName(), author.getLastName());
            }

            BookLiterary checkBookLiterary = blankExchangeSevice.getBookByTitleAndAuthor(bookLiterary.getBookName(), checkAuthor);
            if(checkBookLiterary == null) {
                //checkBookLiterary = blankExchangeSevice.createBookLiterary(bookName, author);
            }

            /*
            OfferList offerList = blankExchangeSevice.createOfferList(bookLiterary,
                    clientState.user,
                    isbn,
                    java.sql.Date.valueOf(year),
                    blankExchangeSevice.getStatusById(1));

            UserList userList = blankExchangeSevice.createUserList(1, offerList.getIdOfferList());

            //TODO: UserValueCategory - еще не тестила эту часть
            for(int i = 0; i < source.length; i++){
                userValueCategoryList.add(blankExchangeSevice.createUserValueCategory(userList, source[i]));
            }
            */

            return "redirect:go_get";
        }

        return "redirect:go_trade";
    }

    @PostMapping("/go_get")
    public String getBookExchange(Model model,
                                  @RequestParam(value = "source", required = false) int[] source){

        if (source != null){
            return "redirect:go_address";
        }
        return "redirect:go_get";
    }

    @PostMapping("/address_form")
    public String getBookExchange(Model model){

        return "redirect:/my_trades#tab_01";
    }
}
