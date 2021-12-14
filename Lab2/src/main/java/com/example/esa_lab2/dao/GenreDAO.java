package com.example.esa_lab2.dao;

import com.example.esa_lab2.dto.Genre;
import lombok.NonNull;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Singleton
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class GenreDAO implements StandartDAO<Genre> {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<Genre> selectAll() {
        return em.createQuery("SELECT g from Genre g", Genre.class).getResultList();
    }

    @Override
    public Genre select(UUID id) {
        return em.find(Genre.class, id);
    }

    @Override
    public void insert(Genre entity) {
        em.persist(entity);
    }

    @Override
    public void update(Genre entity) {
        em.merge(entity);
    }

    @Override
    public void delete(Genre entity) {
        em.remove(entity);
    }

    public Genre findByName(@NonNull String genreName) {
        Genre result = null;
        try {
            result = em.createQuery("SELECT g from Genre g WHERE :genreName like LOWER(g.name)", Genre.class)
                    .setParameter("genreName", genreName)
                    .getSingleResult();
        } catch (NoResultException ignored) { }
        return result;
    }
}
