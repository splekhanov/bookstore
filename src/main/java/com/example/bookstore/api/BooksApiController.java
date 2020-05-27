package com.example.bookstore.api;

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

import javax.validation.Valid;
import java.util.List;

@Controller
public class BooksApiController implements BooksApi {

    @Autowired
    private BooksService booksService;

    @Autowired
    private GenreService genreService;

    @Override
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = booksService.getBook(id);
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<Book> createBook(@ApiParam(value = "book object record", required = true) @Valid @RequestBody Book book) {
        List<Genre> genres = book.getGenres();
        for (Genre genre : genres) {
            genre.setType(genreService.getGenreById(genre.getId()).getType());
        }
        book.setGenres(genres);
        Book savedBook = booksService.createBook(book);
        return ResponseEntity.ok(savedBook);
    }
}
