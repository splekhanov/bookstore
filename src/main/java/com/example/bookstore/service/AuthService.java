package com.example.bookstore.service;

import com.example.bookstore.model.security.Credentials;
import com.example.bookstore.model.security.Token;

public interface AuthService {

    Token authenticateUser(Credentials credentials);

}
