package com.example.bookstore.service;

import com.example.bookstore.model.user.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();

    Role getRole(Long id);

    Role getRoleByName(String name);

}
