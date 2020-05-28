package com.example.bookstore.service;

import com.example.bookstore.db.book.BookRepository;
import com.example.bookstore.exceptions.AlreadyExistException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BookRepository bookRepository;

    @Autowired
    public BooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        bookRepository.findBookByIsbn(book.getIsbn()).ifPresent(e -> {
            throw new AlreadyExistException("Book with ISBN '" + e.getIsbn() + "' is already exists");
        });
        return saveBook(book);
    }

    public Book getBook(Long id) throws NotFoundException {
        Optional<Book> bookOpt = bookRepository.findById(id);

        if (!bookOpt.isPresent()) {
            throw new NotFoundException("Book with ID " + id + " not found");
        }
        return bookOpt.get();
    }

    public Book getBookByIsbn(String isbn) throws NotFoundException {
        Optional<Book> bookOpt = bookRepository.findBookByIsbn(isbn);

        if (!bookOpt.isPresent()) {
            throw new NotFoundException("Book with ISBN " + isbn + " not found");
        }
        return bookOpt.get();
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, Book book) {
        Book bookToUpdate = getBook(id);
        book.setId(bookToUpdate.getId());
        return saveBook(book);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
