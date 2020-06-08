package com.example.bookstore.controller.book;

import com.example.bookstore.controller.BooksApi;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.impl.BooksServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;

@RestController
public class BooksApiController implements BooksApi {

    @Autowired
    private BooksServiceImpl booksService;

    @Override
    public ResponseEntity<Book> createBook(Book book) {
        Book savedBook = booksService.createBook(book);
        URI location = URI.create(String.format("/books/%s", savedBook.getId()));
        return ResponseEntity.created(location).body(savedBook);
    }

    @RolesAllowed("ROLE_MEMBER")
    @Override
    public ResponseEntity<Book> getBookById(Long id) {
        Book book = booksService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<Book> getBookByIsbn(String isbn) {
        Book book = booksService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @ApiOperation(value = "Get all existing books")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book list")})
    @Override
    public ResponseEntity<Iterable<Book>> getBooks() {
        Iterable<Book> books = booksService.getBooks();
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<List<Book>> getBooksByGenre(Long id) {
        List<Book> books = booksService.getBooksByGenre(id);
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<Void> updateBook(Long id, Book book) {
        booksService.updateBook(id, book);
        return ResponseEntity.ok().build();
    }
}