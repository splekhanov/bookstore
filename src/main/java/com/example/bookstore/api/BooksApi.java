package com.example.bookstore.api;

import com.example.bookstore.model.Book;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@Api(value = "books", tags = "Books", description = "Books API")
public interface BooksApi {

    @ApiOperation(value = "Create a book", nickname = "createBook")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book created"),
            @ApiResponse(code = 400, message = "Invalid book"),
            @ApiResponse(code = 409, message = "Book already exists")})
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/books", consumes = {"application/json"})
    ResponseEntity<Book> createBook(@ApiParam(value = "Book object that needs to be added to the store") @Valid @RequestBody Book body);

    @ApiOperation(value = "Get book by id", nickname = "getBookById", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book record", response = Book.class)})
    @GetMapping(value = "/books/{id}", produces = {"application/json"})
    ResponseEntity<Book> getBookById(@ApiParam(value = "ID of book to return", required = true)
                                     @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);

    @ApiOperation(value = "Get book by isbn", nickname = "getBookByIsbn", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book record", response = Book.class)})
    @GetMapping(value = "/books/isbn/{isbn}", produces = {"application/json"})
    ResponseEntity<Book> getBookByIsbn(@ApiParam(value = "ISBN of book to return", required = true)
                                       @NotBlank @Size(max = 13, min = 10, message = "size must be between 10 and 13") @PathVariable String isbn);

    @ApiOperation(value = "Get all existing books", nickname = "getBooks", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book list", response = Book.class, responseContainer = "List")})
    @GetMapping(value = "/books", produces = {"application/json"})
    ResponseEntity<List<Book>> getBooks();

    @ApiOperation(value = "Update existing book", nickname = "updateBook", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book updated", response = Book.class),
            @ApiResponse(code = 400, message = "Invalid book")})
    @PutMapping(value = "/books/{id}", produces = {"application/json"})
    ResponseEntity<Void> updateBook(@ApiParam(value = "ID of book to update", required = true)
                                    @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id,
                                    @ApiParam(value = "Book object record") @RequestBody Book body);

}
