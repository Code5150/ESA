package com.example.esa_lab1.dao;

import com.example.esa_lab1.dto.Book;

import javax.annotation.ManagedBean;
import java.util.List;
import java.util.UUID;

@ManagedBean
public class BookDAO extends AbstractDAO{
    public static List<Book> selectAll() {
        return em.createQuery("SELECT a from Book a", Book.class).getResultList();
    }

    public static Book select(UUID id) {
        var book = em.find(Book.class, id);
        return book;
    }

    public static void insert(Book entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public static void update(Book entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public static void delete(Book entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
}
