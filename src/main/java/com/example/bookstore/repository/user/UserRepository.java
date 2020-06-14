package com.example.bookstore.repository.user;

import com.example.bookstore.model.user.Address;
import com.example.bookstore.repository.BaseRepository;
import com.example.bookstore.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT password FROM user WHERE user.id = ?1", nativeQuery = true)
    String findUserPassword(Long id);
}
