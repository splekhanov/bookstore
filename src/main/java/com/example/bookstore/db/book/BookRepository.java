package com.example.bookstore.db.book;

import com.example.bookstore.db.BaseRepository;
import com.example.bookstore.model.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends BaseRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);
}
