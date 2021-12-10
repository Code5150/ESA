package com.example.esa_lab1.servlets;

import com.example.esa_lab1.Constants;
import com.example.esa_lab1.dao.BookDAO;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(Constants.MAIN_URL)
public class MainServlet extends HttpServlet {

    @Inject
    private BookDAO bookDAO;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("books", bookDAO.selectAll());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}