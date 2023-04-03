package com.noname.books_exchange.repository;

import com.noname.books_exchange.model.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserListRepo extends JpaRepository<UserList, Integer> {
}
