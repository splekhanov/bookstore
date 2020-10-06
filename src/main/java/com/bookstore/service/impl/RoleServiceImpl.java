package com.bookstore.service.impl;

import com.bookstore.exception.NotFoundException;
import com.bookstore.model.user.Role;
import com.bookstore.repository.user.RoleRepository;
import com.bookstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRole(Long id) throws NotFoundException {
        return roleRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Role with ID '%d' not found!", id)));
    }

    @Override
    public Role getRoleByName(String name) throws NotFoundException {
        return roleRepository.findByName(name).orElseThrow(() ->
                new NotFoundException(String.format("Role with name '%s' not found!", name)));
    }
}
