package com.example.bookstore.controller.user;

import com.example.bookstore.controller.UserApi;
import com.example.bookstore.model.security.Credentials;
import com.example.bookstore.model.security.Token;
import com.example.bookstore.model.security.User;
import com.example.bookstore.service.AuthService;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class UserController implements UserApi {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Override
    public ResponseEntity<User> registerUser(User userToRegister) {
        userService.createUser(userToRegister);
        User user = userService.getUserByName(userToRegister.getName());
        URI location = URI.create(String.format("/%s", user.getId()));
        return ResponseEntity.created(location).body(user);
    }

    @Override
    public ResponseEntity<Token> authenticateUser(Credentials credentials) {
        return ok(authService.authenticateUser(credentials));
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ok(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<User> getUserByName(@RequestParam String name) {
        return ok(userService.getUserByName(name));
    }

    @Override
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ok().build();
    }

    @Override
    public ResponseEntity<Void> restoreUser(@PathVariable Long id) {
        userService.restoreUser(id);
        return ok().build();
    }
}
