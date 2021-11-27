package com.example.esa_lab1.dao;

import com.example.esa_lab1.dto.Author;
import com.example.esa_lab1.dto.Genre;
import lombok.NonNull;

import javax.annotation.ManagedBean;
import javax.persistence.NoResultException;
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

    public static Genre findByName(@NonNull String genreName) {
        Genre result = null;
        try {
            result = em.createQuery("SELECT g from Genre g WHERE :genreName like LOWER(g.name)", Genre.class)
                    .setParameter("genreName", genreName)
                    .getSingleResult();
        } catch (NoResultException ignored) { }
        return result;
    }
}
