package com.example.bookstore.service;

import com.example.bookstore.exceptions.AlreadyExistException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.model.Genre;

import java.util.List;

public interface GenreService {

    Genre createGenre(Genre genre) throws AlreadyExistException;

    List<Genre> getGenres();

    Genre getGenreById(Long id) throws NotFoundException;

    Genre updateGenre(Long id, Genre genre) throws NotFoundException;

}
