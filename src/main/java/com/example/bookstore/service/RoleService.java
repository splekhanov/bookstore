package com.example.bookstore.service;

import com.example.bookstore.model.security.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    Role getRole(Long id);

    Role getRoleByName(String name);

}
