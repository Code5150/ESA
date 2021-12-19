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


    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", books=" + books +
                '}';
    }
}
