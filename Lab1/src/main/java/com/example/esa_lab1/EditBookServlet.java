package com.example.esa_lab1;

import com.example.esa_lab1.dao.AuthorDAO;
import com.example.esa_lab1.dao.BookDAO;
import com.example.esa_lab1.dao.GenreDAO;
import com.example.esa_lab1.dto.Author;
import com.example.esa_lab1.dto.Book;
import com.example.esa_lab1.dto.Genre;
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
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@WebServlet("/editBook")
public class EditBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            DateFormat format = new SimpleDateFormat("yyyy");
            var book = BookDAO.select(UUID.fromString(id));
            req.setAttribute("id", id);
            req.setAttribute("name", book.getName());
            req.setAttribute("authors", book.getAuthors().stream().map(Author::getName)
                    .reduce((s, s2) -> s + ", " + s2).orElseThrow().strip()
            );
            req.setAttribute("year", book.getEditionYear().getYear() + 1900);
            req.setAttribute("genres", book.getGenres().stream().map(Genre::getName)
                    .reduce((s, s2) -> s + ", " + s2).orElseThrow().strip()
            );
            req.setAttribute("price", book.getPrice());
            req.setAttribute("description", book.getDescription().strip());
        }

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
        String description = req.getParameter("description").strip();

        String[] authorArray = authors.split(", ");

        String[] genreArray = genre.split(", ");

        Book book = BookDAO.select(UUID.fromString(id));
        book.setName(name);

        /*year - Integer, >0, <= тек.год*/
        if (Integer.parseInt(year) < 0 || Integer.parseInt(year) > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ServletException("Путешествия во времени запрещены");
        }
        DateFormat format = new SimpleDateFormat("yyyy");
        book.setEditionYear(format.parse(year));

        book.setPrice(Integer.parseInt(price));

        book.setDescription(description);

        var newAuthors = new HashSet<Author>();
        for(String authorName: authorArray){
            // проверка есть ли имя автора в БД
            // если нет, то добавить и выдать id
            var author = AuthorDAO.findByName(authorName.toLowerCase());
            if (author == null) {
                author = new Author();
                author.setName(authorName);
                AuthorDAO.insert(author);
            }
            newAuthors.add(author);
        }
        book.setAuthors(newAuthors);

        var newGenres = new HashSet<Genre>();
        for(String g: genreArray){
            // проверка есть ли имя жанра в БД
            // если нет, то добавить и выдать id
            var genreFound = GenreDAO.findByName(g.toLowerCase());
            if (genreFound == null) {
                genreFound = new Genre();
                genreFound.setName(g);
                GenreDAO.insert(genreFound);
            }
            newGenres.add(genreFound);
        }
        book.setGenres(newGenres);

        BookDAO.update(book);

        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
