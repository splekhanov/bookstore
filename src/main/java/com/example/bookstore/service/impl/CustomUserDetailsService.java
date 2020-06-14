package com.example.bookstore.service.impl;

import com.example.bookstore.repository.user.UserRepository;
import com.example.bookstore.model.user.User;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User with email '" + email + "' not found.");
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
