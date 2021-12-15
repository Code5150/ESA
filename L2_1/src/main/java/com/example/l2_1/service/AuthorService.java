package com.example.l2_1.service;

import com.example.l2_1.entity.Author;
import com.example.l2_1.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    protected AuthorRepository authorRepository;

    public List<Author> selectAll() {
        return authorRepository.findAll();
    }

    public Author select(UUID id) {
        return authorRepository.getById(id);
    }

    public void insert(Author author) {
        authorRepository.save(author);
    }

    public Author update(Author author) {
        return authorRepository.save(author);
    }

    public void delete(Author author) {
        authorRepository.delete(author);
    }

    public void delete(UUID id) {
        authorRepository.deleteById(id);
    }
}
