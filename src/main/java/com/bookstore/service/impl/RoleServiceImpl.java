package com.bookstore.service.impl;

import com.bookstore.exception.NotFoundException;
import com.bookstore.service.RoleService;
import com.bookstore.model.user.Role;
import com.bookstore.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Role> roleOpt = roleRepository.findById(id);
        if (!roleOpt.isPresent()) {
            throw new NotFoundException("Role with ID '" + id + "' not found");
        }
        return roleOpt.get();
    }

    @Override
    public Role getRoleByName(String name) throws NotFoundException {
        Optional<Role> roleOpt = roleRepository.findByName(name);
        if (!roleOpt.isPresent()) {
            throw new NotFoundException("Role with name '" + roleOpt + "' not found");
        }
        return roleOpt.get();
    }
}
