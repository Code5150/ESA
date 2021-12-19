package com.example.l2_1.service;

import com.example.l2_1.entity.Book;
import com.example.l2_1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    protected BookRepository bookRepository;

    public List<Book> selectAll() {
        return bookRepository.findAll();
    }

    public Book select(UUID id) {
        return bookRepository.getById(id);
    }

    public void insert(Book book) {
        bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public void delete(UUID id) {
        bookRepository.deleteById(id);
    }
}
