package com.noname.books_exchange.repository;

import com.noname.books_exchange.model.Author;
import com.noname.books_exchange.model.BookLiterary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookLiteraryRepo extends JpaRepository<BookLiterary, Integer> {

    @Query("SELECT b FROM BookLiterary b WHERE b.bookName = ?1 AND b.author = ?2")
    BookLiterary findBookByTitleAndAuthor(String bookName, Author author);
}
