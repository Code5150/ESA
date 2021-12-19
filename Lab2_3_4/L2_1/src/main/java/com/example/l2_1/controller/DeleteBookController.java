package com.example.l2_1.controller;

import com.example.l2_1.entity.Author;
import com.example.l2_1.service.AuthorService;
import com.example.l2_1.service.BookService;
import com.example.l2_1.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/")
public class DeleteBookController {
    @Autowired
    protected BookService bookService;

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam String id, Model model) {
        if (id != null) {
            var book = bookService.select(UUID.fromString(id));
            model.addAttribute("id", id);
            model.addAttribute("name", book.getName());
            model.addAttribute("authors", book.getAuthors().stream().map(Author::getName)
                    .reduce((s, s2) -> s + ", " + s2).orElseThrow()
            );
        }
        return "deletebook";
    }

    @PostMapping("/deleteBook")
    public ModelAndView Book(@RequestParam String id) {
        bookService.delete(UUID.fromString(id));
        return new ModelAndView("redirect:/main");
    }
}

