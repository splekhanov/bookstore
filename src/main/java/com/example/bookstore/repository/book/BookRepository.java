package com.example.bookstore.repository.book;

import com.example.bookstore.repository.BaseRepository;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Genre;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book, Long> {

    Optional<Book> findBookByIsbn(String isbn);

//    @Query(value = "SELECT * FROM book LEFT JOIN genre ON genre.id = book.id WHERE genre.id = ?1", nativeQuery = true)
//    List<Book> findBooksByGenreId(@Param("id")Long id);

    List<Book> findBooksByGenres(Genre genre);
}
