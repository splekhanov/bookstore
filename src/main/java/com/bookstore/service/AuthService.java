package com.bookstore.service;

import com.bookstore.model.security.Token;
import com.bookstore.model.security.Credentials;

public interface AuthService {

    Token authenticateUser(Credentials credentials);

}
