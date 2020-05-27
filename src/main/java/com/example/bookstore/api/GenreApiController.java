package com.example.bookstore.api;

import com.example.bookstore.model.Genre;
import com.example.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GenreApiController implements GenreApi {

    @Autowired
    private GenreService genreService;

    @Override
    public ResponseEntity<List<Genre>> getGenres() {
        List<Genre> genres = genreService.getGenres();
        return ResponseEntity.ok(genres);
    }
}
