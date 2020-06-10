package com.example.bookstore.service;

import com.example.bookstore.model.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    Book getBook(Long id);

    Book getBookByIsbn(String isbn);

    List<Book> getBooks();

    List<Book> getBooksByGenre(Long id);

    void updateBook(Long id, Book book);

}
