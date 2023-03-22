package com.noname.books_exchange.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.noname.books_exchange.model.User;
import com.noname.books_exchange.repository.IUserRepo;

@Service
public class UserService {

    private final PasswordEncoder pwdEncoder;
    private final IUserRepo userRepository;

    @Autowired
    public UserService(IUserRepo userRepo, PasswordEncoder encoder) {
        userRepository = userRepo;
        pwdEncoder = encoder;
    }
    
    public boolean isUserNameUnique(String username) {
        boolean result = userRepository.hasUsername(username);
        return result == false;
    }

    public User createUser(String firstname,
                           String lastname,
                           String surname,
                           String email,
                           String username,
                           String password,
                           byte[] avatar)
    {
        String encodedPassword = pwdEncoder.encode(password);
        User user = new User(firstname, lastname, surname, email, encodedPassword, avatar, username);
        return userRepository.save(user);
    }

    public void enableUser(int id) {
        userRepository.enableUser(id);
    }

    public User findUserByUserName(String username) {
        Optional<User> optional = userRepository.findUserByUserName(username);
        try {
            return optional.get();
        } catch(NoSuchElementException nsee) {
            return null;
        }
    }

    public User findUserByLoginData(String username, String password) {
        Optional<User> optional = userRepository.findUserByUserName(username);
        User result = null;
        try {
            User user = optional.get();
            boolean matchResult = pwdEncoder.matches(password, user.getPassword());
            if(matchResult) {
                result = user;
            }
        } catch (NoSuchElementException nsee) {
            //TODO
        }
        return result;
    }

    public Pair<User, Boolean> handleVKontakteAuth(String firstname,
                                    String lastname,
                                    String email,
                                    String username,
                                    byte[] avatar)
    {
        User user = findUserByUserName(username);
        Boolean emailNeeded = false;
        if(user == null) {
            user = new User(firstname, lastname, email, avatar, username);
            user = userRepository.save(user);
            emailNeeded = true;
        }
        return Pair.of(user, emailNeeded);
    }
}
