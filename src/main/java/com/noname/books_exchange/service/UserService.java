package com.noname.books_exchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noname.books_exchange.repository.IUserRepo;

@Service
public class UserService {
    private final IUserRepo userRepository;

    @Autowired
    public UserService(IUserRepo userRepo) {
        userRepository = userRepo;
    }
    
}
