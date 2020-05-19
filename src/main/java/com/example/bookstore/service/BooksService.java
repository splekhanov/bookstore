package com.example.bookstore.service;

import com.example.bookstore.exceptions.AlreadyExistException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.db.book.BookRepository;
import com.example.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BooksService {

    private final BookRepository bookRepository;

    @Autowired
    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        bookRepository.findBookByTitle(book.getTitle()).ifPresent(e -> {
            throw new AlreadyExistException("Book with with title '" + e.getTitle() + "' is already exists");
        });
        return bookRepository.save(book);
    }

    public Book getBook(Long id) throws NotFoundException {
        Optional<Book> bookOpt = bookRepository.findById(id);

        if (!bookOpt.isPresent()) {
            throw new NotFoundException("Book not found");
        }
        return bookOpt.get();
    }
}
