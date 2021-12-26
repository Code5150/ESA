package com.example.l2_1.entity;

import com.example.l2_1.dto.AuthorDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "author")
@NoArgsConstructor
public class Author extends BaseUuidEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "bio")
    @Getter
    @Setter
    private String bio;

    @ManyToMany(mappedBy = "authors")
    @Getter
    @Setter
    private Set<Book> books = new HashSet<>();


    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", books=" + books +
                '}';
    }

    public Author(AuthorDTO authorDTO) {
        this.id = authorDTO.getId();
        this.bio = authorDTO.getBio();
        this.name = authorDTO.getName();
        this.books = authorDTO.getBooks().stream().map(Book::new).collect(Collectors.toSet());
    }
}
