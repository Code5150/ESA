package com.example.esa_lab1;

import com.example.esa_lab1.dao.BookDAO;
import com.example.esa_lab1.dto.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/editbook.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String authors =  req.getParameter("author");
        String year = req.getParameter("year");
        String genre = req.getParameter("genre");
        String price = req.getParameter("price");
        String description = req.getParameter("description");

        String[] authorArray = authors.replace(" ", "").split(",");

        String[] genreArray = genre.replace(" ", "").split(",");

        var book = BookDAO.select(UUID.fromString(id));

        book.setName(name);

        DateFormat format = new SimpleDateFormat("yyyy");
        book.setEditionYear(format.parse(year));

        book.setPrice(Integer.parseInt(price));

        book.setDescription(description);


        for(String authorName: authorArray){
            // проверка есть ли имя автора в БД
            // если нет, то добавить и выдать id
        }

        for(String g: genreArray){
            // проверка есть ли имя жанра в БД
            // если нет, то добавить и выдать id
        }

        BookDAO.update(book);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
