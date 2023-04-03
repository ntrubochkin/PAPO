package com.noname.books_exchange.service;

import com.noname.books_exchange.model.*;
import com.noname.books_exchange.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BlankExchangeSevice {
    private final ICategoryRepo categoryRepo;
    private final IAuthorRepo authorRepo;
    private final IBookLiteraryRepo bookLiteraryRepo;
    private final IOfferListRepo offerListRepo;
    private final IStatusRepo statusRepo;
    private final IUserListRepo userListRepo;
    private final IUserValueCategoryRepo userValueCategoryRepo;

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
    public BlankExchangeSevice(ICategoryRepo categoryRepo,
                               IAuthorRepo authorRepo,
                               IBookLiteraryRepo bookLiteraryRepo,
                               IOfferListRepo offerListRepo, IStatusRepo statusRepo, IUserListRepo userListRepo, IUserValueCategoryRepo userValueCategoryRepo) {
        this.categoryRepo = categoryRepo;
        this.authorRepo = authorRepo;
        this.bookLiteraryRepo = bookLiteraryRepo;
        this.offerListRepo = offerListRepo;
        this.statusRepo = statusRepo;
        this.userListRepo = userListRepo;
        this.userValueCategoryRepo = userValueCategoryRepo;
    }

    public List<Category> findCategoriesByIdParent(int id_categories){
        return categoryRepo.findCategoriesByIdParent(id_categories);
    }

    public void setAllCategories(){
        genre = categoryRepo.findCategoriesByIdParent(2);
        science_field = categoryRepo.findCategoriesByIdParent(3);
        state = categoryRepo.findCategoriesByIdParent(4);
        cover = categoryRepo.findCategoriesByIdParent(5);
        laureate = categoryRepo.findCategoriesByIdParent(6);
        film_adaptation = categoryRepo.findCategoriesByIdParent(7);
        publication_language = categoryRepo.findCategoriesByIdParent(8);
    }

    public Author getAuthorByName(String firsName, String lastName){
        return authorRepo.findAuthorByName(firsName, lastName);
    }

    public Author createAuthor(String firsName, String lastName) {
        return authorRepo.save(new Author(lastName, firsName));
    }

    public BookLiterary getBookByTitleAndAuthor(String bookName, Author author){
        return bookLiteraryRepo.findBookByTitleAndAuthor(bookName, author);
    }

    public BookLiterary createBookLiterary(String bookName, Author author){
        return bookLiteraryRepo.save(new BookLiterary(author, bookName));
    }

    public OfferList createOfferList(BookLiterary bookLiterary, User user, String isbn, Date yearPublishing, Status status){
        //TODO: ??? Добавить параметры
        return offerListRepo.save(new OfferList(bookLiterary, user, isbn, yearPublishing, status));
    }

    public Status getStatusById(int id){
        return statusRepo.findStatusById(id);
    }

    public UserList createUserList (int typeList, int idList){
        return userListRepo.save(new UserList(typeList, idList));
    }

    public UserValueCategory createUserValueCategory(UserList userList, int categoryId){
        return userValueCategoryRepo.save(new UserValueCategory(userList, categoryRepo.findCategoryByIdCategory(categoryId)));
    }
}
