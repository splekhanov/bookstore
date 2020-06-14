package com.example.bookstore.repository.user;

import com.example.bookstore.repository.BaseRepository;
import com.example.bookstore.model.user.Role;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(String role);
}
