package com.example.l2_1.entity;

import com.example.l2_1.dto.GenreDTO;
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
@Table(name = "genre")
@NoArgsConstructor
public class Genre extends BaseUuidEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @ManyToMany(mappedBy = "genres")
    @Getter
    @Setter
    private Set<Book> books = new HashSet<>();

    @Override
    public String toString() {
        return "Genre{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }

    public Genre(GenreDTO genreDTO) {
        this.id = genreDTO.getId();
        this.name = genreDTO.getName();
        this.books = genreDTO.getBooks().stream().map(Book::new).collect(Collectors.toSet());
    }
}
