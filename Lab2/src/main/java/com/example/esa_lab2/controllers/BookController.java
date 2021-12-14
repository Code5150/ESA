package com.example.esa_lab2.controllers;

import com.example.esa_lab2.dto.Author;
import com.example.esa_lab2.dto.Book;
import com.example.esa_lab2.dto.Genre;
import com.example.esa_lab2.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.text.ParseException;

@Controller
public class BookController {

    private BookService bookService;

    @RequestMapping("/")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getListBook());
        return "index";
    }

    @GetMapping("/newBook")
    public String newBook() {
        return "newbook";
    }

    @PostMapping("newBook")
    public String addNewBook(@RequestParam String name,
                             @RequestParam String author,
                             @RequestParam String year,
                             @RequestParam String genre,
                             @RequestParam String price,
                             @RequestParam String description,
                             Model model
    ) throws ServletException, ParseException {

        bookService.newBook(name, author, year, genre, price, description);

        model.addAttribute("books", bookService.getListBook());
        return "redirect:index";
    }

    @GetMapping("/editBook")
    public String getEditBook(@RequestParam String id, Model model){

        Book book = bookService.getBook(id);

        model.addAttribute("id", book.getId());
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
        return "editbook";

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

        bookService.editBook(id, name, author, year, genre, price, description);

        model.addAttribute("listBooks", bookService.getListBook());
        return "redirect:index";
    }

    @GetMapping("/deleteBook")
    public String getDeleteBook(@RequestParam String id, Model model){

        Book book = bookService.getBook(id);
        model.addAttribute("id", book.getId());
        model.addAttribute("name", book.getName());
        model.addAttribute("authors", book.getAuthors().stream().map(Author::getName)
                .reduce((s, s2) -> s + ", " + s2).orElseThrow());
        return "deletebook";
    }

    @DeleteMapping("/deleteBook")
    public String deleteBook(@RequestParam String id, Model model){

        bookService.deleteBook(id);
        model.addAttribute("listBooks", bookService.getListBook());
        return "redirect:";
    }
}
