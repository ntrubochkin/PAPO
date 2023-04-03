package com.noname.books_exchange.utils;

import com.noname.books_exchange.model.Category;
import com.noname.books_exchange.service.BlankExchangeSevice;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
public class BlankExchange {

    private final BlankExchangeSevice blankExchangeSevice;

    public BlankExchange(BlankExchangeSevice blankExchangeSevice) {
        this.blankExchangeSevice = blankExchangeSevice;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        blankExchangeSevice.setAllCategories();
    }

    public void setCategories(Model model){
        model.addAttribute(PageAttributes.GENRE, blankExchangeSevice.getGenre());
        model.addAttribute(PageAttributes.SCIENCE_FIELD, blankExchangeSevice.getScience_field());
        model.addAttribute(PageAttributes.STATE, blankExchangeSevice.getState());
        model.addAttribute(PageAttributes.COVER, blankExchangeSevice.getCover());
        model.addAttribute(PageAttributes.LAUREATE, blankExchangeSevice.getLaureate());
        model.addAttribute(PageAttributes.FILM_ADAPTATION, blankExchangeSevice.getFilm_adaptation());
        model.addAttribute(PageAttributes.PUBLICATION_LANGUAGE, blankExchangeSevice.getPublication_language());
    }
}
