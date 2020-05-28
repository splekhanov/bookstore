package com.example.bookstore.service;

import com.example.bookstore.db.genre.GenreRepository;
import com.example.bookstore.exceptions.AlreadyExistException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Genre createGenre(Genre genre) {
        genreRepository.findGenreByType(genre.getType()).ifPresent(e -> {
            throw new AlreadyExistException("Genre '" + e.getType() + "' is already exists");
        });
        return genreRepository.save(genre);
    }

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) throws NotFoundException {
        Optional<Genre> genre = genreRepository.findById(id);
        if (!genre.isPresent()) {
            throw new NotFoundException("Genre not found");
        }
        return genre.get();
    }

    public Genre editGenre(Genre genre) {
        genreRepository.findById(genre.getId())
                .orElseThrow(() -> new NotFoundException("Genre with id '" + genre.getId() + "' not found"));
        return genreRepository.save(genre);
    }
}
