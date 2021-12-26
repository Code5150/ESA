package com.example.l2_1.dto;

import com.example.l2_1.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BookDTO {
    private UUID id;
    private String name;
    private String description;
    private Date editionYear;
    private Integer price;
    @With private Set<AuthorDTO> authors;
    @With private Set<GenreDTO> genres;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.price = book.getPrice();
        this.editionYear = book.getEditionYear();
        //this.authors = book.getAuthors().stream().map(a -> new AuthorDTO(a).withBooks(null)).collect(Collectors.toSet());
        //this.genres = book.getGenres().stream().map(g -> new GenreDTO(g).withBooks(null)).collect(Collectors.toSet());
    }
}
