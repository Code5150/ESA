package com.example.esa_lab2.utils;

import com.example.esa_lab2.dao.AuthorDAO;
import com.example.esa_lab2.dao.BookDAO;
import com.example.esa_lab2.dao.GenreDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class HibernateUtils {

    @PersistenceContext
    private static EntityManager em;

    public static boolean entityManagerExists() {
        return em != null;
    }

    public static void createEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "bookShop" );
        em = emf.createEntityManager();
        setDaoEntityManager(em);
    }

    public static void setDaoEntityManager(EntityManager e) {
        AuthorDAO.setEm(e);
        BookDAO.setEm(e);
        GenreDAO.setEm(e);
    }

    public static void destroyEntityManager() {
        if (em.getEntityManagerFactory().isOpen()) {
            em.getEntityManagerFactory().close();
        }
        em.close();
    }
}