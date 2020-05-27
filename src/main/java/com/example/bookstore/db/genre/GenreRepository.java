package com.example.bookstore.db.genre;

import com.example.bookstore.db.BaseRepository;
import com.example.bookstore.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends BaseRepository<Genre, Long> {

    Optional<Genre> findGenreByType(String genre);
}
