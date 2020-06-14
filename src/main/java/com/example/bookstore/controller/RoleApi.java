package com.example.bookstore.controller;

import com.example.bookstore.model.user.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Validated
@Tag(name = "role", description = "Roles API")
@Api(tags = "role", description = "Roles API")
public interface RoleApi {

    @io.swagger.annotations.ApiOperation(value = "Get all existing user roles", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Genre list", response = Role.class, responseContainer = "List")})
    @Operation(summary = "Get all existing user roles", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Role.class))))})
    @GetMapping(value = "/roles", produces = {"application/json"})
    ResponseEntity<List<Role>> getRoles();

}
