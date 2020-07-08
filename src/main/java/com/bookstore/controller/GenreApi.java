package com.bookstore.controller;

import com.bookstore.model.Genre;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@Tag(name = "genre", description = "Genres API")
public interface GenreApi {

    @Operation(summary = "Add new genre", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genre created"),
            @ApiResponse(responseCode = "409", description = "Genre already exists")})
    @PostMapping(value = "/genres", consumes = {"application/json"})
    ResponseEntity<Genre> createGenre(@Parameter(description = "Genre object that needs to be added to the store") @Valid @RequestBody Genre body);


    @Operation(summary = "Get all existing genres", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Genre.class))))})
    @GetMapping(value = "/genres", produces = {"application/json"})
    ResponseEntity<List<Genre>> getGenres();


    @Operation(summary = "Get genre by id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre record", content = @Content(schema = @Schema(implementation = Genre.class))),
            @ApiResponse(responseCode = "404", description = "Genre not found")})
    @GetMapping(value = "/genres/{id}", produces = {"application/json"})
    ResponseEntity<Genre> getGenreById(@Parameter(description = "ID of genre to return", required = true)
                                       @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);


    @Operation(summary = "Update an existing genre", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genre updated", content = @Content(schema = @Schema(implementation = Genre.class))),
            @ApiResponse(responseCode = "404", description = "Genre not found")})
    @PutMapping(value = "/genre/{id}", produces = {"application/json"})
    ResponseEntity<Genre> updateGenre(@Parameter(description = "ID of genre to update", required = true)
                                      @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id,
                                      @Parameter(description = "Genre object record") @RequestBody Genre body);
}
