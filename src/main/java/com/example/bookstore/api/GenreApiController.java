package com.example.bookstore.api;

import com.example.bookstore.model.Genre;
import com.example.bookstore.service.impl.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
public class GenreApiController implements GenreApi {

    @Autowired
    private GenreServiceImpl genreService;

    @Override
    public ResponseEntity<Genre> createGenre(@Valid Genre genre) {
        Genre savedGenre = genreService.createGenre(genre);
        URI location = URI.create(String.format("/genres/%s", savedGenre.getId()));
        return ResponseEntity.created(location).body(savedGenre);
    }

    @Override
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) {
        Genre genre = genreService.getGenreById(id);
        return ResponseEntity.ok(genre);
    }

    @Override
    public ResponseEntity<List<Genre>> getGenres() {
        List<Genre> genres = genreService.getGenres();
        return ResponseEntity.ok(genres);
    }

    @Override
    public ResponseEntity<Void> updateGenre(Long id, Genre genre) {
        genreService.updateGenre(id, genre);
        return ResponseEntity.ok().build();
    }
}
