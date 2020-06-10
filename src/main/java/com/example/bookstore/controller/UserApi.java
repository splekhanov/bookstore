package com.example.bookstore.controller;

import com.example.bookstore.model.security.Credentials;
import com.example.bookstore.model.security.Role;
import com.example.bookstore.model.security.Token;
import com.example.bookstore.model.security.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ExampleProperty;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import java.util.List;

@Validated
@Tag(name = "user", description = "Users API")
@Api(tags = "user", description = "Users API")
public interface UserApi {

    @io.swagger.annotations.ApiOperation(value = "Create user")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "User created", response = User.class),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid user"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "User already exists")})
    @Operation(summary = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user"),
            @ApiResponse(responseCode = "409", description = "User already exists")})
    @PostMapping(value = "/users/registration", consumes = {"application/json"})
    ResponseEntity<User> registerUser(@Parameter(description = "User object", required = true) @Valid @RequestBody User user);


    @io.swagger.annotations.ApiOperation(value = "Authorize user and get Bearer token")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Token created", response = Token.class),
            @io.swagger.annotations.ApiResponse(code = 401, message = "Invalid login or password provided")})
    @Operation(summary = "Authorize user and get Bearer token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token created", content = @Content(schema = @Schema(implementation = Token.class))),
            @ApiResponse(responseCode = "401", description = "Invalid login or password provided")})
    @PostMapping(value = "/users/auth", consumes = {"application/json"})
    ResponseEntity<Token> authenticateUser(@Parameter(description = "Credentials need to be provided to generate token") @Valid @RequestBody Credentials credentials);


    @io.swagger.annotations.ApiOperation(value = "Get all existing users", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "User list", response = User.class, responseContainer = "List")})
    @Operation(summary = "Get all existing users", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User records", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))})
    @GetMapping(value = "/users", produces = {"application/json"})
    ResponseEntity<List<User>> getUsers();


    @io.swagger.annotations.ApiOperation(value = "Get user by ID", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "User record", response = User.class),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid user"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "User not found")})
    @Operation(summary = "Get user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User record", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "/users/{id}", produces = {"application/json"})
    ResponseEntity<User> getUserById(@Parameter(description = "ID of user to return", required = true)
                                     @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);


    @io.swagger.annotations.ApiOperation(value = "Get user by name", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "User record", response = User.class),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid user"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "User not found")})
    @Operation(summary = "Get user by name", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User record", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "/users/name/{name}", produces = {"application/json"})
    ResponseEntity<User> getUserByName(@Parameter(description = "Name of user to return", required = true)
                                       @NotBlank @PathVariable String name);


    @io.swagger.annotations.ApiOperation(value = "Delete user by ID", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "User has been deleted"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "User not found")})
    @Operation(summary = "Delete user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @DeleteMapping(value = "/users/{id}")
    ResponseEntity<Void> deleteUser(@Parameter(description = "ID of user to be deleted", required = true)
                                    @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);


    @io.swagger.annotations.ApiOperation(value = "Restore user by ID", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "User has been restored"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "User not found")})
    @Operation(summary = "Restore user by ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been restored"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @PutMapping(value = "/users/{id}/restore")
    ResponseEntity<Void> restoreUser(@Parameter(description = "ID of user to be restored", required = true)
                                     @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);


    @io.swagger.annotations.ApiOperation(value = "Get all existing user roles", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Genre list", response = Role.class, responseContainer = "List")})
    @Operation(summary = "Get all existing user roles", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Role.class))))})
    @GetMapping(value = "/users/roles", produces = {"application/json"})
    ResponseEntity<List<Role>> getRoles();

}
