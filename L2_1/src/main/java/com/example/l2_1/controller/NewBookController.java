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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/")
public class NewBookController {
    @Autowired
    protected AuthorService authorService;
    @Autowired
    protected BookService bookService;
    @Autowired
    protected GenreService genreService;

    @GetMapping("/newBook")
    protected String newBook() {
        return "newbook";
    }

    @SneakyThrows
    @PostMapping("/newBook")
    public ModelAndView persistNewBook(@RequestBody MultiValueMap<String, String> params) {
        String name = params.getFirst("name");
        String authors =  params.getFirst("author");
        String year = params.getFirst("year");
        String genre = params.getFirst("genre");
        String price = params.getFirst("price");
        String description = params.getFirst("description");

        String[] authorArray = authors.split(", ");

        String[] genreArray = genre.split(", ");

        Book book = new Book();
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
