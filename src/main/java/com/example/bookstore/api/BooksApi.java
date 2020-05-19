package com.example.bookstore.api;

import com.example.bookstore.model.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Api(value = "books")
public interface BooksApi {

    @ApiOperation(value = "Get book by id", nickname = "getBookById", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book record created", response = Book.class)})
    @RequestMapping(value = "/books/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Book> getBookById(@ApiParam(value = "ID of book to return", required = true) @PathVariable Long id);

    @ApiOperation(value = "Create a book", nickname = "createBook")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book created"),
            @ApiResponse(code = 400, message = "Invalid book"),
            @ApiResponse(code = 409, message = "Book already exists")})
    @RequestMapping(value = "/books",
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Book> createBook(@ApiParam(value = "Book object that needs to be added to the store") @Valid @RequestBody Book body);

}
