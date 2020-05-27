package com.example.bookstore.api;

import com.example.bookstore.model.Genre;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "genres", tags = "Genres", description = "Genres API")
public interface GenreApi {

    @ApiOperation(value = "Get all existing genres", nickname = "getGenres", response = Genre.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Genre list", response = Genre.class)})
    @RequestMapping(value = "/genres",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Genre>> getGenres();

}
