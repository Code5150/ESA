package com.example.esa_lab1.dao;

import com.example.esa_lab1.dto.Author;

import javax.annotation.ManagedBean;
import java.util.List;
import java.util.UUID;

@ManagedBean
public class AuthorDAO extends AbstractDAO{

    public static List<Author> selectAll() {
        return em.createQuery("SELECT a from Author a", Author.class).getResultList();
    }

    public static Author select(UUID id) {
        var author = em.find(Author.class, id);
        return author;
    }

    public static void insert(Author entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public static void update(Author entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public static void delete(Author entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
}
