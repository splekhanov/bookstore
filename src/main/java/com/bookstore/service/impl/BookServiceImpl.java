package com.bookstore.service.impl;

import com.bookstore.exception.AlreadyExistException;
import com.bookstore.exception.NotFoundException;
import com.bookstore.model.Book;
import com.bookstore.model.Genre;
import com.bookstore.repository.book.BookRepository;
import com.bookstore.service.BookService;
import com.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.genreService = genreService;
    }

    @Override
    public Book createBook(Book book) {
        findAndSetGenres(book);
        bookRepository.findBookByIsbn(book.getIsbn()).ifPresent(e -> {
            throw new AlreadyExistException("Book with ISBN '" + e.getIsbn() + "' already exists");
        });
        return saveBook(book);
    }

    @Override
    public Book getBook(Long id) throws NotFoundException {
        return bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Book with ID '%d' not found!", id)));
    }

    @Override
    public Book getBookByIsbn(String isbn) throws NotFoundException {
        return bookRepository.findBookByIsbn(isbn).orElseThrow(() ->
                new NotFoundException(String.format("Book with ISBN '%s' not found!", isbn)));
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getBooksByGenre(Long id) {
        Genre genre = genreService.getGenreById(id);
        return bookRepository.findBooksByGenres(genre);
    }

    @Override
    public void updateBook(Long id, Book book) {
        Book bookToUpdate = getBook(id);
        book.setId(bookToUpdate.getId());
        findAndSetGenres(book);
        saveBook(book);
    }

    private Book saveBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    private void findAndSetGenres(Book book) {
        List<Genre> genres = book.getGenres();
        genres.forEach(g -> g.setId(genreService.getGenreByType(g.getType()).getId()));
        book.setGenres(genres);
    }
}
