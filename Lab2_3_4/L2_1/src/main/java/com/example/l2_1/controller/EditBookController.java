package com.example.l2_1.controller;

import com.example.l2_1.entity.Author;
import com.example.l2_1.entity.Book;
import com.example.l2_1.entity.Genre;
import com.example.l2_1.service.AuthorService;
import com.example.l2_1.service.BookService;
import com.example.l2_1.service.GenreService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class EditBookController {
    @Autowired
    protected AuthorService authorService;
    @Autowired
    protected BookService bookService;
    @Autowired
    protected GenreService genreService;

    @GetMapping("/editBook")
    public String editBook(@RequestParam String id, Model model) {
        if (id != null) {
            DateFormat format = new SimpleDateFormat("yyyy");
            var book = bookService.select(UUID.fromString(id));
            model.addAttribute("id", id);
            model.addAttribute("name", book.getName());
            model.addAttribute("authors", book.getAuthors().stream().map(Author::getName)
                    .reduce((s, s2) -> s + ", " + s2).orElseThrow().strip()
            );
            model.addAttribute("year", book.getEditionYear().getYear() + 1900);
            model.addAttribute("genres", book.getGenres().stream().map(Genre::getName)
                    .reduce((s, s2) -> s + ", " + s2).orElseThrow().strip()
            );
            model.addAttribute("price", book.getPrice());
            model.addAttribute("description", book.getDescription().strip());
        }
        return "editbook";
    }

    @SneakyThrows
    @PostMapping("/editBook")
    public ModelAndView updateBook(@RequestBody MultiValueMap<String, String> params) {
        String id = params.getFirst("id");
        String name = params.getFirst("name");
        String authors =  params.getFirst("author");
        String year = params.getFirst("year");
        String genre = params.getFirst("genre");
        String price = params.getFirst("price");
        String description = params.getFirst("description");

        String[] authorArray = authors.split(", ");

        String[] genreArray = genre.split(", ");

        Book book = bookService.select(UUID.fromString(id));
        book.setName(name);

        if (Integer.parseInt(year) < 0 || Integer.parseInt(year) > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Путешествия во времени запрещены");
        }
        DateFormat format = new SimpleDateFormat("yyyy");
        book.setEditionYear(format.parse(year));

        book.setPrice(Integer.parseInt(price));

        book.setDescription(description);

        for(String authorName: authorArray){
            // проверка есть ли имя автора в БД
            // если нет, то добавить и выдать id
            var author = authorService.findByName(authorName.toLowerCase());
            if (author == null) {
                author = new Author();
                author.setName(authorName);
                authorService.insert(author);
            }
            book.getAuthors().add(author);
        }

        for(String g: genreArray){
            // проверка есть ли имя жанра в БД
            // если нет, то добавить и выдать id
            var genreFound = genreService.findByName(g.toLowerCase());
            if (genreFound == null) {
                genreFound = new Genre();
                genreFound.setName(g);
                genreService.insert(genreFound);
            }
            book.getGenres().add(genreFound);
        }
        bookService.insert(book);
        return new ModelAndView("redirect:/main");
    }
}

