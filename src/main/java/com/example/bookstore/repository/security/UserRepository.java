package com.example.bookstore.repository.security;

import com.example.bookstore.repository.BaseRepository;
import com.example.bookstore.model.security.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByName(String userName);
}
