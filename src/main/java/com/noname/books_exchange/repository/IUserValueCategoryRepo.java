package com.noname.books_exchange.repository;

import com.noname.books_exchange.model.UserValueCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserValueCategoryRepo extends JpaRepository<UserValueCategory, Integer> {
}
