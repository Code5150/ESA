package com.example.esa_lab1.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "genre")
public class Genre extends BaseUuidEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @ManyToMany(mappedBy = "genres")
    @Getter
    @Setter
    private Set<Book> books = new HashSet<>();
}
