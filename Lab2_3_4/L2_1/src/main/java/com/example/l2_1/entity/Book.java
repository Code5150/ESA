package com.example.l2_1.entity;

import com.example.l2_1.dto.BookDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "book")
@NoArgsConstructor
public class Book extends BaseUuidEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "author_book",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    @Getter
    @Setter
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "genre_book",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    @Getter
    @Setter
    private Set<Genre> genres = new HashSet<>();

    @Column(name = "edition_year")
    @Getter
    @Setter
    private Date editionYear;

    @Column(name = "price")
    @Getter
    @Setter
    private Integer price;

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", editionYear=" + editionYear +
                ", price=" + price +
                '}';
    }

    public Book(BookDTO bookDTO) {
        this.id = bookDTO.getId();
        this.name = bookDTO.getName();
        this.description = bookDTO.getDescription();
        this.editionYear = bookDTO.getEditionYear();
        this.price = bookDTO.getPrice();
        this.authors = bookDTO.getAuthors().stream().map(Author::new).collect(Collectors.toSet());
        this.genres = bookDTO.getGenres().stream().map(Genre::new).collect(Collectors.toSet());
    }
}
