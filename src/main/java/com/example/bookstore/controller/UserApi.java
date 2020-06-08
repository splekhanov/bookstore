package com.example.bookstore.controller;

import com.example.bookstore.model.security.Credentials;
import com.example.bookstore.model.security.Token;
import com.example.bookstore.model.security.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@Tag(name = "user", description = "Users API")
public interface UserApi {

    @Operation(summary = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid user"),
            @ApiResponse(responseCode = "409", description = "User already exists")})
    @PostMapping(value = "/users/registration", consumes = {"application/json"})
    ResponseEntity<User> registerUser(@Parameter(description = "User object", required = true) @Valid @RequestBody User user);

    @Operation(summary = "Authorize user and get Bearer token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token created"),
            @ApiResponse(responseCode = "401", description = "Invalid login or password provided")})
    @PostMapping(value = "/users/auth", consumes = {"application/json"})
    ResponseEntity<Token> authenticateUser(@Parameter(description = "Credentials need to be provided to generate token") @Valid @RequestBody Credentials credentials);

    @Operation(summary = "Get user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User record", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "/users/{id}", consumes = {"application/json"})
    ResponseEntity<User> getUserById(@Parameter(description = "ID of user to return", required = true)
                                     @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);

    @Operation(summary = "Get user by name", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User record", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "/users/name/{name}", consumes = {"application/json"})
    ResponseEntity<User> getUserByName(@Parameter(description = "Name of user to return", required = true)
                                       @NotBlank @PathVariable String name);

    @Operation(summary = "Delete user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @DeleteMapping(value = "/users/{id}", consumes = {"application/json"})
    ResponseEntity<Void> deleteUser(@Parameter(description = "ID of user to be deleted", required = true)
                                    @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);

    @Operation(summary = "Restore user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been restored"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @PutMapping(value = "/users/{id}/restore", consumes = {"application/json"})
    ResponseEntity<Void> restoreUser(@Parameter(description = "ID of user to be restored", required = true)
                                     @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);

}
