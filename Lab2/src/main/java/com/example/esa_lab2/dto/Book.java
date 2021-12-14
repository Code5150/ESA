package com.example.esa_lab2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book extends BaseUuidEntity  {

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
}
