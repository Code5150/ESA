package com.example.l2_1.dto;

import com.example.l2_1.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class GenreDTO {
    private UUID id;
    private String name;
    @With private Set<BookDTO> books;

    public GenreDTO(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
        //this.books = genre.getBooks().stream().map(b -> new BookDTO(b).withGenres(null)).collect(Collectors.toSet());
    }
}
