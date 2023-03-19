package com.noname.books_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noname.books_exchange.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    
}
