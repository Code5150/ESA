package com.example.l2_1.service;

import com.example.l2_1.entity.Author;
import com.example.l2_1.entity.Genre;
import com.example.l2_1.repository.AuthorRepository;
import com.example.l2_1.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GenreService {
    @Autowired
    protected GenreRepository genreRepository;

    public List<Genre> selectAll() {
        return genreRepository.findAll();
    }

    public Genre select(UUID id) {
        return genreRepository.getById(id);
    }

    public void insert(Genre genre) {
        genreRepository.save(genre);
    }

    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }

    public void delete(Genre genre) {
        genreRepository.delete(genre);
    }

    public void delete(UUID id) {
        genreRepository.deleteById(id);
    }

    public Genre findByName(String name) { return genreRepository.findByNameIgnoreCase(name); }
}
