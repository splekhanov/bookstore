package com.example.bookstore.controller.role;

import com.example.bookstore.controller.RoleApi;
import com.example.bookstore.model.user.Role;
import com.example.bookstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController implements RoleApi {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleService.getRoles();
        return ResponseEntity.ok(roles);
    }
}
