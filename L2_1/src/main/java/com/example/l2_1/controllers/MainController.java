package com.example.l2_1.controllers;

import com.example.l2_1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    protected BookService bookService;

    @GetMapping("/main")
    public String index(Model model) {
        model.addAttribute("books", bookService.selectAll());
        return "index";
    }
}
