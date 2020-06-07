package com.example.bookstore.repository.genre;

import com.example.bookstore.repository.BaseRepository;
import com.example.bookstore.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends BaseRepository<Genre, Long> {

    Optional<Genre> findGenreByType(String genre);

}
