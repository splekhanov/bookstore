package com.bookstore.repository.user;

import com.bookstore.repository.BaseRepository;
import com.bookstore.model.user.Role;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface RoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(String role);
}
