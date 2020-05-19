package com.example.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book implements IdentifiedEntity{

    private static final long serialVersionUID = 4435398303003335465L;

    public Book(String author, String isbn, String title, String publishedYear, String price) {
        this.author = author;
        this.isbn = isbn;
        this.title = title;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "published_year", nullable = false)
    private String publishedYear;

    @Column(name = "price")
    private String price;
}
