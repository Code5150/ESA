package com.example.l2_1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

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
