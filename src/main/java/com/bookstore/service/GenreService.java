package com.bookstore.service;

import com.bookstore.exception.AlreadyExistException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.model.Genre;

import java.util.List;

public interface GenreService {

    Genre createGenre(Genre genre) throws AlreadyExistException;

    List<Genre> getGenres();

    Genre getGenreById(Long id) throws NotFoundException;

    Genre updateGenre(Long id, Genre genre) throws NotFoundException;

}
