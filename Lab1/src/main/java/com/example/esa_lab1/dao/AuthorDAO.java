package com.example.esa_lab1.dao;

import com.example.esa_lab1.dto.Author;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

@Singleton
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class AuthorDAO implements StandardDAO<Author>{

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<Author> selectAll() {
        return em.createQuery("SELECT a from Author a", Author.class).getResultList();
    }

    @Override
    public Author select(UUID id) {
        return em.find(Author.class, id);
    }

    @Override
    public void insert(Author entity) {
        em.persist(entity);
    }

    @Override
    public void update(Author entity) {
        em.merge(entity);
    }

    @Override
    public void delete(Author entity) {
        em.remove(entity);
    }

    public Author findByName(@NonNull String authorName) {
        Author result = null;
        try {
            result = em.createQuery("SELECT a from Author a WHERE :authorName like LOWER(a.name)", Author.class)
                    .setParameter("authorName", authorName)
                    .getSingleResult();
        } catch (NoResultException ignored) { }
        return result;
    }
}
