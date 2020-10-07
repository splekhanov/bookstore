package com.bookstore.repository.user;

import com.bookstore.repository.BaseRepository;
import com.bookstore.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT u.password FROM User u WHERE u.id = ?1")
    String findUserPassword(Long id);

}
