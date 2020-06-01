package com.example.bookstore.service.impl;

import com.example.bookstore.db.genre.GenreRepository;
import com.example.bookstore.exceptions.AlreadyExistException;
import com.example.bookstore.exceptions.BadRequestException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.model.Genre;
import com.example.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre createGenre(Genre genre) throws AlreadyExistException {
        if(genre.getId() != null) {
            Optional<Genre> existingGenre = genreRepository.findById(genre.getId());
            if(existingGenre.isPresent()) {
                throw new AlreadyExistException("Genre with ID '" + genre.getId() + "' already exists. Use 'PUT genres/" + genre.getId() + "' to update existing record");
            }
        }
        genreRepository.findGenreByType(genre.getType()).ifPresent(e -> {
            throw new AlreadyExistException("Genre '" + e.getType() + "' already exists");
        });
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Long id) throws NotFoundException {
        Optional<Genre> genre = genreRepository.findById(id);
        if (!genre.isPresent()) {
            throw new NotFoundException("Genre with ID '" + id + "' not found");
        }
        return genre.get();
    }

    @Override
    public void updateGenre(Long id, Genre genre) throws NotFoundException {
        Long objectId = genre.getId();
        if (objectId != null) {
            if (!id.equals(objectId)) {
                throw new BadRequestException("ID parameter doesn't match object's ID! Object ID may be omitted.");
            }
        }
        Genre bookToUpdate = getGenreById(id);
        genre.setId(bookToUpdate.getId());
        saveGenre(genre);
    }

    private Genre saveGenre(Genre genre) {
        return genreRepository.saveAndFlush(genre);
    }
}
