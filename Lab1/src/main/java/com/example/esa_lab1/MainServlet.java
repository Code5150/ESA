package com.example.esa_lab1;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    private String message;
    private EntityManager em;

    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "bookShop" );
        em = emf.createEntityManager();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void destroy() {
        if (em.getEntityManagerFactory().isOpen()) {
            em.getEntityManagerFactory().close();
        em.close();
    }
}
        }