package com.example.bookstore.service;

import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.model.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    Book getBook(Long id) throws NotFoundException;

    Book getBookByIsbn(String isbn) throws NotFoundException;

    Iterable<Book> getBooks();

    List<Book> getBooksByGenre(Long id);

    void updateBook(Long id, Book book);

}
