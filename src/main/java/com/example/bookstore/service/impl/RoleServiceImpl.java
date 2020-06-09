package com.example.bookstore.service.impl;

import com.example.bookstore.exceptions.AlreadyExistException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.model.security.Role;
import com.example.bookstore.model.security.User;
import com.example.bookstore.repository.security.RoleRepository;
import com.example.bookstore.repository.security.UserRepository;
import com.example.bookstore.service.RoleService;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleReposiroty) {
        this.roleRepository = roleReposiroty;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
