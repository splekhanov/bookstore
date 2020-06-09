package com.example.bookstore.service.impl;

import com.example.bookstore.exceptions.AlreadyExistException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.model.security.Role;
import com.example.bookstore.model.security.User;
import com.example.bookstore.repository.security.RoleRepository;
import com.example.bookstore.repository.security.UserRepository;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.bookstore.utils.constants.Roles.USER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder bCryptPasswordEncoder,
                           RoleRepository roleReposiroty) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleReposiroty;
    }

    @Override
    public void createUser(User user) {
        checkIfUserExists(user);
        checkAndSetRoles(user);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = findUserById(id);
        if (!user.isEnabled()) {
            throw new NotFoundException("User has been deleted");
        } else {
            user.setEnabled(false);
            userRepository.save(user);
        }
    }

    @Override
    public void restoreUser(Long id) {
        User userToRestore = findUserById(id);
        if (userToRestore.isEnabled()) {
            throw new AlreadyExistException(String.format("User with ID %d is already active and doesn't need to be restored", id));
        } else {
            userToRestore.setEnabled(true);
            userRepository.save(userToRestore);
        }
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return findUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        User user = userRepository.findByName(name).orElseThrow(() ->
                new NotFoundException(String.format("User with name '%s' not found!", name)));
        user.setPassword("");
        return user;
    }

    private User findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("User with name '%d' not found!", id)));
        user.setPassword("");
        return user;
    }

    private void checkIfUserExists(User user) {
        Optional<User> existingUserById;
        Optional<User> existingUserByName = userRepository.findByName(user.getName());
        if (existingUserByName.isPresent()) {
            throw new AlreadyExistException(String.format("User with name '%s' already exists!", user.getName()));
        }
        if (user.getId() != null) {
            existingUserById = userRepository.findById(user.getId());
            if (existingUserById.isPresent()) {
                throw new AlreadyExistException(String.format("User with ID '%s' already exists!", user.getId()));
            }
        }
    }

    private void checkAndSetRoles(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Collections.singletonList(roleRepository.findByName(USER).get()));
        }
        List<Long> availableRolesIds = roleRepository.findAll().stream().map(Role::getId).collect(Collectors.toList());
        List<Long> userRolesIds = user.getRoles().stream().map(Role::getId).collect(Collectors.toList());
        for (Long userRolesId : userRolesIds) {
            if (!availableRolesIds.contains(userRolesId)) {
                throw new NotFoundException(String.format("Role with ID '%d' not found", userRolesId));
            }
        }
        List<Role> rolesToSet = roleRepository.findAll().stream().filter(role -> userRolesIds.stream()
                .anyMatch(i -> role.getId().equals(i))).collect(Collectors.toList());
        user.setRoles(rolesToSet);
    }
}
