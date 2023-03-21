package com.noname.books_exchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noname.books_exchange.model.UserAddress;

@Repository
public interface IUserAddressRepo extends JpaRepository<UserAddress, Integer> {
    
}
