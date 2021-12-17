package com.example.esa_lab2.services;

import com.example.esa_lab2.dao.AuthorDAO;
import com.example.esa_lab2.dao.BookDAO;
import com.example.esa_lab2.dao.GenreDAO;
import com.example.esa_lab2.dto.Author;
import com.example.esa_lab2.dto.Book;
import com.example.esa_lab2.dto.Genre;

import javax.inject.Inject;
import javax.servlet.ServletException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class BookService {

    @Inject
    protected AuthorDAO authorDAO;

    @Inject
    protected BookDAO bookDAO;

    @Inject
    protected GenreDAO genreDAO;


    public List<Book> getListBook() {
        return bookDAO.selectAll();
    }

    public Book getBook(String uuid) {
        return bookDAO.select((UUID.fromString(uuid)));
    }

    public void newBook(
            String name,
            String authors,
            String year,
            String genre,
            String price,
            String description
    ) throws ParseException, ServletException {
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
    }

    public void editBook(
            String id,
            String name,
            String authors,
            String year,
            String genre,
            String price,
            String description
    ) throws ServletException, ParseException {
        String[] authorArray = authors.split(", ");

        String[] genreArray = genre.split(", ");

        Book book = bookDAO.select(UUID.fromString(id));
        book.setName(name);

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
            var author = authorDAO.findByName(authorName.toLowerCase());
            if (author == null) {
                author = new Author();
                author.setName(authorName);
                authorDAO.insert(author);
            }
            newAuthors.add(author);
        }
        book.setAuthors(newAuthors);

        var newGenres = new HashSet<Genre>();
        for(String g: genreArray){
            // проверка есть ли имя жанра в БД
            // если нет, то добавить и выдать id
            var genreFound = genreDAO.findByName(g.toLowerCase());
            if (genreFound == null) {
                genreFound = new Genre();
                genreFound.setName(g);
                genreDAO.insert(genreFound);
            }
            newGenres.add(genreFound);
        }
        book.setGenres(newGenres);

        bookDAO.update(book);
    }

    public void deleteBook(String uuid) {
        bookDAO.delete(UUID.fromString(uuid));
    }
}
