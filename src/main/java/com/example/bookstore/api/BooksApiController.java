package com.example.bookstore.api;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.impl.BooksServiceImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@Controller
public class BooksApiController implements BooksApi {

    @Autowired
    private BooksServiceImpl booksService;

    @Override
    public ResponseEntity<Book> createBook(@ApiParam(value = "book object record", required = true) @RequestBody Book book) {
        Book savedBook = booksService.createBook(book);
        URI location = URI.create(String.format("/books/%s", savedBook.getId()));
        return ResponseEntity.created(location).body(savedBook);
    }

    @Override
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = booksService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<Book> getBookByIsbn(@PathVariable("isbn") String isbn) {
        Book book = booksService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = booksService.getBooks();
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable Long id) {
        List<Book> books = booksService.getBooksByGenre(id);
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<Void> updateBook(Long id, Book book) {
        booksService.updateBook(id, book);
        return ResponseEntity.ok().build();
    }
}
