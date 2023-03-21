package com.noname.books_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.noname.books_exchange.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT EXISTS(SELECT u FROM User u WHERE u.userName = ?1)")
    boolean hasUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.enabled = TRUE WHERE u.idUser = ?1")
    void enableUser(int id);

    Optional<User> findUserByUserName(String login);
}