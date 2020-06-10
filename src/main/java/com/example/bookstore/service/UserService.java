package com.example.bookstore.service;

import com.example.bookstore.model.security.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    List<User> getUsers();

    User getUserById(Long id);

    User getUserByName(String name);

    void restoreUser(Long id);

    void deleteUserById(Long id);
}
