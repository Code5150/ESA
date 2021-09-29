package com.example.esa_lab1.dao;

import com.example.esa_lab1.dto.Genre;

import javax.annotation.ManagedBean;
import java.util.List;
import java.util.UUID;

@ManagedBean
public class GenreDAO extends AbstractDAO{

    public static List<Genre> selectAll() {
        return em.createQuery("SELECT g from Genre g", Genre.class).getResultList();
    }

    public static Genre select(UUID id) {
        var genre = em.find(Genre.class, id);
        return genre;
    }

    public static void insert(Genre entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public static void update(Genre entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public static void delete(Genre entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }
}
