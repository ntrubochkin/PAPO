package com.noname.books_exchange.service;

import com.noname.books_exchange.model.Category;
import com.noname.books_exchange.repository.IBlankExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlankExchangeSevice {
    private final IBlankExchangeRepo blankExchangeRepo;

    private List<Category> genre;
    private List<Category> science_field;
    private List<Category> state;
    private List<Category> cover;
    private List<Category> laureate;
    private List<Category> film_adaptation;
    private List<Category> publication_language;

    public List<Category> getGenre() {
        return genre;
    }

    public List<Category> getScience_field() {
        return science_field;
    }

    public List<Category> getState() {
        return state;
    }

    public List<Category> getCover() {
        return cover;
    }

    public List<Category> getLaureate() {
        return laureate;
    }

    public List<Category> getFilm_adaptation() {
        return film_adaptation;
    }

    public List<Category> getPublication_language() {
        return publication_language;
    }

    @Autowired
    public BlankExchangeSevice(IBlankExchangeRepo blankExchangeRepo) {
        this.blankExchangeRepo = blankExchangeRepo;
    }

    public List<Category> findCategoriesByIdParent(int id_categories){
        return blankExchangeRepo.findCategoriesByIdParent(id_categories);
    }

    public void setAllCategories(){
        genre = blankExchangeRepo.findCategoriesByIdParent(2);
        science_field = blankExchangeRepo.findCategoriesByIdParent(3);
        state = blankExchangeRepo.findCategoriesByIdParent(4);
        cover = blankExchangeRepo.findCategoriesByIdParent(5);
        laureate = blankExchangeRepo.findCategoriesByIdParent(6);
        film_adaptation = blankExchangeRepo.findCategoriesByIdParent(7);
        publication_language = blankExchangeRepo.findCategoriesByIdParent(8);
    }
}
