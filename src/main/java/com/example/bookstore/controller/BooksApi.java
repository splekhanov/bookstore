package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Validated
@Tag(name = "book", description = "Books API")
@Api(tags = "book", description = "Books API")
public interface BooksApi {

    @io.swagger.annotations.ApiOperation(value = "Create a book", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 201, message = "Book created", response = Book.class),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid book"),
            @io.swagger.annotations.ApiResponse(code = 409, message = "Book already exists")})
    @Operation(summary = "Create a book", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created", content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "400", description = "Invalid book"),
            @ApiResponse(responseCode = "409", description = "Book already exists")})
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/books", consumes = {"application/json"})
    ResponseEntity<Book> createBook(@Parameter(description = "Book object that needs to be added to the store", required = true) @Valid @RequestBody Book body);


    @io.swagger.annotations.ApiOperation(value = "Get book by id", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Book record", response = Book.class)})
    @Operation(summary = "Get book by id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book record", content = @Content(schema = @Schema(implementation = Book.class)))})
    @GetMapping(value = "/books/{id}", produces = {"application/json"})
    ResponseEntity<Book> getBookById(@Parameter(description = "ID of book to return", required = true)
                                     @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);


    @io.swagger.annotations.ApiOperation(value = "Get book by isbn", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Book record", response = Book.class)})
    @Operation(summary = "Get book by isbn", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book record", content = @Content(schema = @Schema(implementation = Book.class)))})
    @GetMapping(value = "/books/isbn/{isbn}", produces = {"application/json"})
    ResponseEntity<Book> getBookByIsbn(@Parameter(description = "ISBN of book to return", required = true)
                                       @NotBlank @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "must consist of 10 or 13 digits with or without hyphens. Spaces are not allowed.") @PathVariable String isbn);


    @io.swagger.annotations.ApiOperation(value = "Get all existing books", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Book list", response = Book.class, responseContainer = "List")})
    @Operation(summary = "Get all existing books", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))})
    @GetMapping(value = "/books", produces = {"application/json"})
    ResponseEntity<List<Book>> getBooks();


    @io.swagger.annotations.ApiOperation(value = "Get books by genre ID", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Book list", response = Book.class, responseContainer = "List")})
    @Operation(summary = "Get books by genre ID", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book list", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))})
    @GetMapping(value = "/books/genre/{id}", produces = {"application/json"})
    ResponseEntity<List<Book>> getBooksByGenre(@Parameter(description = "ID of genre", required = true)
                                               @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id);


    @io.swagger.annotations.ApiOperation(value = "Update existing book", authorizations = @Authorization(value = "Authorization"))
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "Book updated"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "Invalid book")})
    @Operation(summary = "Update existing book", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated"),
            @ApiResponse(responseCode = "400", description = "Invalid book")})
    @PutMapping(value = "/books/{id}", produces = {"application/json"})
    ResponseEntity<Void> updateBook(@Parameter(description = "ID of book to update", required = true)
                                    @Min(value = 1, message = "must be greater than or equal to 1") @PathVariable Long id,
                                    @Parameter(description = "Book object record") @RequestBody Book body);
}
