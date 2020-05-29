package com.example.bookstore.api;

import com.example.bookstore.model.Genre;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@Api(value = "genres", tags = "Genres", description = "Genres API")
public interface GenreApi {

    @ApiOperation(value = "Create a genre", nickname = "createGenre")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Genre created"),
            @ApiResponse(code = 400, message = "Invalid genre"),
            @ApiResponse(code = 409, message = "Genre already exists")})
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/genres", consumes = {"application/json"})
    ResponseEntity<Genre> createGenre(@ApiParam(value = "Genre object that needs to be added to the store") @Valid @RequestBody Genre body);

    @ApiOperation(value = "Get all existing genres", nickname = "getGenres", response = Genre.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Genre list", response = Genre.class)})
    @RequestMapping(value = "/genres",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Genre>> getGenres();

    @ApiOperation(value = "Get genre by id", nickname = "getGenreById", response = Genre.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Genre record", response = Genre.class)})
    @GetMapping(value = "/genres/{id}", produces = {"application/json"})
    ResponseEntity<Genre> getGenreById(@ApiParam(value = "ID of genre to return", required = true)
                                       @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);

    @ApiOperation(value = "Update existing genre", nickname = "updateGenre", response = Genre.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Genre updated", response = Genre.class),
            @ApiResponse(code = 400, message = "Invalid genre")})
    @PutMapping(value = "/genre/{id}", produces = {"application/json"})
    ResponseEntity<Void> updateGenre(@ApiParam(value = "ID of genre to update", required = true)
                                     @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id,
                                     @ApiParam(value = "Genre object record") @RequestBody Genre body);

}
