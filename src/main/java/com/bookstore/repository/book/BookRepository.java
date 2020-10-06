package com.bookstore.repository.book;

import com.bookstore.model.Book;
import com.bookstore.repository.BaseRepository;
import com.bookstore.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book, Long> {

    Optional<Book> findBookByIsbn(String isbn);

    List<Book> findBooksByGenres(Genre genre);
}
