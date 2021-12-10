package com.example.esa_lab1.servlets;


import com.example.esa_lab1.Constants;
import com.example.esa_lab1.dao.AuthorDAO;
import com.example.esa_lab1.dao.BookDAO;
import com.example.esa_lab1.dao.GenreDAO;
import com.example.esa_lab1.dto.Author;
import com.example.esa_lab1.dto.Book;
import com.example.esa_lab1.dto.Genre;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@WebServlet("/newBook")
public class NewBookServlet  extends HttpServlet {

    @Inject
    protected AuthorDAO authorDAO;

    @Inject
    protected BookDAO bookDAO;

    @Inject
    protected GenreDAO genreDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/newbook.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String authors =  req.getParameter("author");
        String year = req.getParameter("year");
        String genre = req.getParameter("genre");
        String price = req.getParameter("price");
        String description = req.getParameter("description");

        String[] authorArray = authors.split(", ");

        String[] genreArray = genre.split(", ");

        Book book = new Book();
        book.setName(name);

        if (Integer.parseInt(year) < 0 || Integer.parseInt(year) > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ServletException("Путешествия во времени запрещены");
        }
        DateFormat format = new SimpleDateFormat("yyyy");
        book.setEditionYear(format.parse(year));

        book.setPrice(Integer.parseInt(price));

        book.setDescription(description);

        for(String authorName: authorArray){
            // проверка есть ли имя автора в БД
            // если нет, то добавить и выдать id
            var author = authorDAO.findByName(authorName.toLowerCase());
            if (author == null) {
                author = new Author();
                author.setName(authorName);
                authorDAO.insert(author);
            }
            book.getAuthors().add(author);
        }

        for(String g: genreArray){
            // проверка есть ли имя жанра в БД
            // если нет, то добавить и выдать id
            var genreFound = genreDAO.findByName(g.toLowerCase());
            if (genreFound == null) {
                genreFound = new Genre();
                genreFound.setName(g);
                genreDAO.insert(genreFound);
            }
            book.getGenres().add(genreFound);
        }
        bookDAO.insert(book);

        resp.sendRedirect(req.getContextPath() + Constants.MAIN_URL);
        //getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
