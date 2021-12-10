package com.example.esa_lab1.dao;

import com.example.esa_lab1.dto.Book;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Singleton
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class BookDAO implements StandardDAO<Book> {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<Book> selectAll() {
        return em.createQuery("SELECT a from Book a", Book.class).getResultList();
    }

    @Override
    public Book select(UUID id) {
        return em.find(Book.class, id);
    }

    @Override
    public void insert(Book entity) {
        em.persist(entity);
    }

    @Override
    public void update(Book entity) {
        em.merge(entity);
    }

    @Override
    public void delete(Book entity) {
        em.remove(entity);
    }

    public void delete(UUID id) {
        em.remove(em.getReference(Book.class, id));
    }
}
