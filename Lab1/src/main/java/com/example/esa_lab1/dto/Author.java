package com.example.esa_lab1.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "author")
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
}
