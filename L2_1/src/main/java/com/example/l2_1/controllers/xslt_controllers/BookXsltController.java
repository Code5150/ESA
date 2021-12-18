package com.example.l2_1.controllers.xslt_controllers;


import com.example.l2_1.entities.Book;
import com.example.l2_1.services.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/xslt/books")
public class BookXsltController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ModelAndView getBooks() throws JsonProcessingException {
        List<Book> bookList = bookService.selectAll();
        ModelAndView modelAndView = new ModelAndView("book");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(bookList)));
        modelAndView.addObject(source);
        return modelAndView;
    }
}
