package com.example.esa_lab1;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private EntityManager em;

    public void init() {
        message = "Hello World!";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "bookShop" );
        em = emf.createEntityManager();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
        if (em.getEntityManagerFactory().isOpen()) {
            em.getEntityManagerFactory().close();
        }
        em.close();
    }
}