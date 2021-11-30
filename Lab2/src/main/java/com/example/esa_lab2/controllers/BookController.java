package com.example.esa_lab2.controllers;

import com.example.esa_lab2.dao.AuthorDAO;
import com.example.esa_lab2.dao.BookDAO;
import com.example.esa_lab2.dao.GenreDAO;
import com.example.esa_lab2.dto.Author;
import com.example.esa_lab2.dto.Book;
import com.example.esa_lab2.dto.Genre;
import com.example.esa_lab2.utils.BookList;
import jakarta.servlet.ServletException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.UUID;

@Controller
public class BookController {

    @RequestMapping(value="/")
    public String getListBooks(Model model) {
        BookList bookList = new BookList(BookDAO.selectAll());
        model.addAttribute("listBooks", bookList);
        return "index";
    }

    @RequestMapping(value = "/newBook", method = RequestMethod.GET)
    public String newBook() {
        return "redirect:/newbook";
    }

    @RequestMapping(value="/newBook", method = RequestMethod.POST)
    public String addNewBook(@RequestParam String name,
                             @RequestParam String author,
                             @RequestParam String year,
                             @RequestParam String genre,
                             @RequestParam String price,
                             @RequestParam String description,
                             Model model
    ) throws ServletException, ParseException {

        String[] authorArray = author.split(", ");

        String[] genreArray = genre.split(", ");

        Book book = new Book();
        book.setName(name);

        /*year - Integer, >0, <= тек.год*/
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
            var authorByName = AuthorDAO.findByName(authorName.toLowerCase());
            if (authorByName == null) {
                authorByName = new Author();
                authorByName.setName(authorName);
                AuthorDAO.insert(authorByName);
            }
            book.getAuthors().add(authorByName);
        }
        for(String g: genreArray){
            // проверка есть ли имя жанра в БД
            // если нет, то добавить и выдать id
            var genreFound = GenreDAO.findByName(g.toLowerCase());
            if (genreFound == null) {
                genreFound = new Genre();
                genreFound.setName(g);
                GenreDAO.insert(genreFound);
            }
            book.getGenres().add(genreFound);
        }
        BookDAO.insert(book);

        BookList bookList = new BookList(BookDAO.selectAll());
        model.addAttribute("listBooks", bookList);
        return "redirect:/index";
    }

    @RequestMapping(value="/editBook", method = RequestMethod.GET)
    public String getEditBook(@RequestParam String id, Model model){

        Book book = BookDAO.select(UUID.fromString(id));
        model.addAttribute("id", id);
        model.addAttribute("name", book.getName());
        String author = book.getAuthors().stream().map(Author::getName)
                .reduce((s, s2) -> s + ", " + s2).orElseThrow().strip();
        model.addAttribute("author", author);
        model.addAttribute("year", book.getEditionYear().getYear() + 1900);
        String genre = book.getGenres().stream().map(Genre::getName)
                .reduce((s, s2) -> s + ", " + s2).orElseThrow().strip();
        model.addAttribute("genre", genre);
        model.addAttribute("price", book.getPrice());
        model.addAttribute("description", book.getDescription().strip());
        return "redirect:/editbook";

    }

    @RequestMapping(value="/editBook", method = RequestMethod.POST)
    public String editBook(
                           @RequestParam String id,
                           @RequestParam String name,
                           @RequestParam String author,
                           @RequestParam String year,
                           @RequestParam String genre,
                           @RequestParam String price,
                           @RequestParam String description,
                           Model model) throws ServletException, ParseException {

        String[] authorArray = author.split(", ");

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
            var authorByName = AuthorDAO.findByName(authorName.toLowerCase());
            if (authorByName == null) {
                authorByName = new Author();
                authorByName.setName(authorName);
                AuthorDAO.insert(authorByName);
            }
            newAuthors.add(authorByName);
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

        BookList bookList = new BookList(BookDAO.selectAll());
        model.addAttribute("listBooks", bookList);
        return "redirect:/index";
    }

    @RequestMapping(value="/deleteBook", method = RequestMethod.GET)
    public String getDeleteBook(@RequestParam String id, Model model){

        Book book = BookDAO.select(UUID.fromString(id));
        model.addAttribute("book", book);
        return "redirect:/deletebook";
    }

    @RequestMapping(value="/deleteBook", method = RequestMethod.DELETE)
    public String deleteBook(@RequestParam String id, Model model){

        Book book = BookDAO.select(UUID.fromString(id));
        BookDAO.delete(book);
        BookList bookList = new BookList(BookDAO.selectAll());
        model.addAttribute("listBooks", bookList);
        return "redirect:/deletebook";
    }



}
