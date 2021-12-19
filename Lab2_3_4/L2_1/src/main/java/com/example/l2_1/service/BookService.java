package com.example.l2_1.service;

import com.example.l2_1.entity.Book;
import com.example.l2_1.repository.BookRepository;
import com.example.l2_1.service.jms.Sender;
import com.example.l2_1.util.DBChanges;
import com.example.l2_1.util.ListConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    private Sender sender;

    public List<Book> selectAll() {


        List<Book> bookList = bookRepository.findAll();

        sender.logging("book", DBChanges.READE,
                ListConverter.listToString(Collections.singletonList(bookList)));

        return bookRepository.findAll();
    }

    public Book select(UUID id) {

        Book book = bookRepository.getById(id);
        sender.logging("book", DBChanges.READE, book);

        return book;
    }

    public void insert(Book book) {

        sender.logging("book", DBChanges.CREATE, book);
        bookRepository.save(book);
    }

    public Book update(Book book) {

        sender.logging("book", DBChanges.UPDATE, book);
        return bookRepository.save(book);
    }

    public void delete(Book book) {

        sender.logging("book", DBChanges.DELETE, book);
        bookRepository.delete(book);
    }

    public void delete(UUID id) {

        sender.logging("book", DBChanges.DELETE, id);
        bookRepository.deleteById(id);
    }
}
