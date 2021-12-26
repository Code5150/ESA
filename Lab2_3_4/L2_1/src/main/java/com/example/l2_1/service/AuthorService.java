package com.example.l2_1.service;

import com.example.l2_1.entity.Author;
import com.example.l2_1.jms.Sender;
import com.example.l2_1.repository.AuthorRepository;
import com.example.l2_1.util.DBChanges;
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

        sender.logging("author", DBChanges.READ,
                "list authors, number of records: " + authorList.size());

        return authorList;
    }

    public Author select(UUID id) {
        Author author = authorRepository.getById(id);

        sender.logging("author", DBChanges.READ, author);

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

    public Author findByName(String name) {

        Author author = authorRepository.findByNameIgnoreCase(name);

        sender.logging("author", DBChanges.READ, author);
        return author;
    }
}
