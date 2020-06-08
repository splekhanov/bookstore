package com.example.bookstore.service.impl;

import com.example.bookstore.repository.security.UserRepository;
import com.example.bookstore.model.security.User;
import com.example.bookstore.security.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User with name '" + username + "' not found.");
        }
        return UserPrincipal.create(user.orElse(null));
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User with name '" + id + "' not found.");
        }
        return UserPrincipal.create(user);
    }
}
