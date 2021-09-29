package com.example.esa_lab1.utils;

import com.example.esa_lab1.dao.AuthorDAO;
import com.example.esa_lab1.dao.BookDAO;
import com.example.esa_lab1.dao.GenreDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtils {
    private static EntityManager em;

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
