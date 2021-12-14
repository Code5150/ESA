package com.example.esa_lab2.dao;

import com.example.esa_lab2.dto.Book;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Singleton
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class BookDAO implements StandartDAO<Book> {

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
    public static void insert(Book entity) {
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
