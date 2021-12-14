package com.example.esa_lab2.dao;

import com.example.esa_lab2.dto.Author;
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
public class AuthorDAO implements StandartDAO<Author>{

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
    public static void insert(Author entity) {
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
