package com.example.l2_1.services;

import com.example.l2_1.entities.Author;
import com.example.l2_1.repositories.AuthorRepository;
import com.example.l2_1.services.jms.Sender;
import com.example.l2_1.utils.DBChanges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {
    @Autowired
    protected AuthorRepository authorRepository;

    @Autowired
    private Sender sender;

    public List<Author> selectAll() {
        List<Author> authorList = authorRepository.findAll();

        sender.logging("author", DBChanges.READE, authorList);
        return authorList;
    }

    public Author select(UUID id) {
        Author author = authorRepository.getById(id);

        sender.logging("author", DBChanges.READE, author);
        return author;
    }

    public void insert(Author author) {

        sender.logging("author", DBChanges.CREATE, author);
        authorRepository.save(author);
    }

    public Author update(Author author) {

        sender.logging("author", DBChanges.UPDATE, author);
        return authorRepository.save(author);
    }

    public void delete(Author author) {

        sender.logging("author", DBChanges.DELETE, author);
        authorRepository.delete(author);
    }

    public void delete(UUID id) {

        sender.logging("author", DBChanges.DELETE, id);
        authorRepository.deleteById(id);
    }
}
