package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book implements IdentifiedEntity {

    @Id
    @ApiModelProperty(position = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @ApiModelProperty(position = 2)
    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "ISBN must consist of 10 or 13 digits with or without hyphens. Spaces are not allowed.")
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @ApiModelProperty(position = 3)
    @Column(name = "title", nullable = false)
    private String title;

    @ApiModelProperty(position = 4)
    @Column(name = "author", nullable = false)
    private String author;

    @ApiModelProperty(position = 5)
    @Column(name = "publication_year", nullable = false)
    private String publicationYear;

    @ApiModelProperty(position = 6)
    @NotBlank(message = "Price is mandatory")
    @Column(name = "price")
    private String price;

    @ApiModelProperty(position = 7)
    @NotNull(message = "Quantity is mandatory")
    @Column(name = "quantity")
    private Integer quantity;

    @ApiModelProperty(position = 8)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(
                    name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres;
}
