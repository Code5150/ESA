package com.example.esa_lab2.dao;

import com.example.esa_lab2.dto.Author;
import lombok.NonNull;

import javax.annotation.ManagedBean;
import javax.persistence.NoResultException;
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

    public static Author findByName(@NonNull String authorName) {
        Author result = null;
        try {
            result = em.createQuery("SELECT a from Author a WHERE :authorName like LOWER(a.name)", Author.class)
                    .setParameter("authorName", authorName)
                    .getSingleResult();
        } catch (NoResultException ignored) { }
        return result;
    }
}