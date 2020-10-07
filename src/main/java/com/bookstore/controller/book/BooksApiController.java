package com.bookstore.controller.book;

import com.bookstore.controller.BooksApi;
import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class BooksApiController implements BooksApi {

    private BookService bookService;

    @Autowired
    public BooksApiController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<Book> createBook(Book book) {
        Book savedBook = bookService.createBook(book);
        URI location = URI.create(String.format("/books/%s", savedBook.getId()));
        return ResponseEntity.created(location).body(savedBook);
    }

    @Override
    public ResponseEntity<Book> getBookById(Long id) {
        Book book = bookService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<Book> getBookByIsbn(String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks();
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<List<Book>> getBooksByGenre(Long id) {
        List<Book> books = bookService.getBooksByGenre(id);
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<Void> updateBook(Long id, Book book) {
        bookService.updateBook(id, book);
        return ResponseEntity.ok().build();
    }

}
