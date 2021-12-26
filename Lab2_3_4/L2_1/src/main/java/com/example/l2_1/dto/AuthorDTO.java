package com.example.l2_1.dto;

import com.example.l2_1.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class AuthorDTO {
    private UUID id;
    private String name;
    private String bio;
    @With private Set<BookDTO> books;

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.bio = author.getBio();
        //this.books = author.getBooks().stream().map(b -> new BookDTO(b).withAuthors(null)).collect(Collectors.toSet());
    }
}
