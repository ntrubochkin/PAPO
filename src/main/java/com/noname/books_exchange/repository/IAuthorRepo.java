package com.noname.books_exchange.repository;

import com.noname.books_exchange.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepo extends JpaRepository<Author, Integer> {

    @Query("SELECT a FROM Author a WHERE a.firstName = ?1 AND a.lastName = ?2")
    Author findAuthorByName(String firstName, String lastName);
}
