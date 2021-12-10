package com.example.esa_lab1.servlets;

import com.example.esa_lab1.Constants;
import com.example.esa_lab1.dao.BookDAO;
import com.example.esa_lab1.dto.Author;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {

    @Inject
    protected BookDAO bookDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            var book = bookDAO.select(UUID.fromString(id));
            req.setAttribute("id", id);
            req.setAttribute("name", book.getName());
            req.setAttribute("authors", book.getAuthors().stream().map(Author::getName)
                    .reduce((s, s2) -> s + ", " + s2).orElseThrow()
            );
        }
        getServletContext().getRequestDispatcher("/deletebook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookDAO.delete(UUID.fromString(id));
        resp.sendRedirect(req.getContextPath() + Constants.MAIN_URL);
    }
}
