package com.example.bookstore.api;

import com.example.bookstore.exceptions.BadRequestException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Genre;
import com.example.bookstore.service.BooksService;
import com.example.bookstore.service.GenreService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
public class BooksApiController implements BooksApi {

    @Autowired
    private BooksService booksService;

    @Autowired
    private GenreService genreService;

    @Override
    public ResponseEntity<Book> createBook(@ApiParam(value = "book object record", required = true) @Valid @RequestBody Book book) {
        List<Genre> genres = book.getGenres();
        for (Genre genre : genres) {
            genre.setType(genreService.getGenreById(genre.getId()).getType());
        }
        book.setGenres(genres);
        Book savedBook = booksService.createBook(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedBook);
    }

    @Override
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = booksService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = booksService.getBooks();
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<Void> updateBook(Long id, Book book) {
        Long objectId = book.getId();
        if (objectId != null) {
            if (!id.equals(objectId)) {
                throw new BadRequestException("ID parameter doesn't match object's ID! Object ID may be omitted.");
            }
        }
        Book existingBook = booksService.getBook(id);

        if(objectId != null) {
            book.setId(existingBook.getId());
        }
        booksService.saveBook(book);
        return ResponseEntity.ok().build();
    }
}
