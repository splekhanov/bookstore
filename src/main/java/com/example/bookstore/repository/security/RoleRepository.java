package com.example.bookstore.repository.security;

import com.example.bookstore.repository.BaseRepository;
import com.example.bookstore.model.security.Role;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(String role);
}
