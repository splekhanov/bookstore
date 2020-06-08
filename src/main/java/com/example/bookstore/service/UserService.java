package com.example.bookstore.service;

import com.example.bookstore.model.security.User;

public interface UserService {

    void createUser(User user);

    User getUserById(Long id);

    User getUserByName(String name);

    void restoreUser(Long id);

    void deleteUserById(Long id);
}
