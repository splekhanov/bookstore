package com.bookstore.service.impl;

import com.bookstore.exception.AlreadyExistException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.model.Genre;
import com.bookstore.repository.genre.GenreRepository;
import com.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre createGenre(Genre genre) throws AlreadyExistException {
        Long genreId = genre.getId();
        String genreType = genre.getType();
        if (genreId != null) {
            genreRepository.findById(genreId).ifPresent(e -> {
                throw new AlreadyExistException(String.format("Genre with ID '%d' already exists. Use 'PUT genres/%d' to update existing record", genreId, genreId));
            });
        }
        if (genreType != null) {
            genreRepository.findGenreByType(genreType).ifPresent(e -> {
                throw new AlreadyExistException(String.format("Genre '%s' already exists", genreType));
            });
        }
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Long id) throws NotFoundException {
        return genreRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Genre with ID '%d' not found!", id)));
    }

    @Override
    public Genre getGenreByType(String type) throws NotFoundException {
        return genreRepository.findGenreByType(type).orElseThrow(() ->
                new NotFoundException(String.format("Genre with type '%s' not found!", type)));
    }

    @Override
    public Genre updateGenre(Long id, Genre genre) throws NotFoundException {
        Genre genreToUpdate = getGenreById(id);
        genre.setId(genreToUpdate.getId());
        return saveGenre(genre);
    }

    private Genre saveGenre(Genre genre) {
        return genreRepository.saveAndFlush(genre);
    }
}
