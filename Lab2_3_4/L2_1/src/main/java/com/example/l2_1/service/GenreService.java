package com.example.l2_1.service;

import com.example.l2_1.entity.Author;
import com.example.l2_1.entity.Genre;
import com.example.l2_1.repository.AuthorRepository;
import com.example.l2_1.repository.GenreRepository;
import com.example.l2_1.service.jms.Sender;
import com.example.l2_1.util.DBChanges;
import com.example.l2_1.util.ListConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class GenreService {
    @Autowired
    protected GenreRepository genreRepository;

    @Autowired
    private Sender sender;

    public List<Genre> selectAll() {

        List<Genre> genreList = genreRepository.findAll();

        sender.logging("genre", DBChanges.READE,
                ListConverter.listToString(Collections.singletonList(genreList)));

        return genreList;
    }

    public Genre select(UUID id) {

        Genre genre = genreRepository.getById(id);

        sender.logging("genre", DBChanges.READE, genre);
        return genre;
    }

    public void insert(Genre genre) {

        sender.logging("genre", DBChanges.CREATE, genre);
        genreRepository.save(genre);
    }

    public Genre update(Genre genre) {

        sender.logging("genre", DBChanges.UPDATE, genre);
        return genreRepository.save(genre);
    }

    public void delete(Genre genre) {

        sender.logging("genre", DBChanges.DELETE, genre);
        genreRepository.delete(genre);
    }

    public void delete(UUID id) {

        sender.logging("genre", DBChanges.DELETE, id);
        genreRepository.deleteById(id);
    }

    public Genre findByName(String name) {

        Genre genre = genreRepository.findByNameIgnoreCase(name);

        sender.logging("genre", DBChanges.READE, genre);
        return genre;
    }
}
