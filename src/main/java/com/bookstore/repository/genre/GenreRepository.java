package com.bookstore.repository.genre;

import com.bookstore.model.Genre;
import com.bookstore.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends BaseRepository<Genre, Long> {

    Optional<Genre> findGenreByType(String genre);

}
