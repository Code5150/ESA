package com.example.l2_1.controllers.xslt_controllers;


import com.example.l2_1.entity.Author;
import com.example.l2_1.service.AuthorService;
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
@RequestMapping("/xslt/authors")
public class AuthorXsltController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ModelAndView getAuthors() throws JsonProcessingException {
        List<Author> authorList = authorService.selectAll();
        ModelAndView modelAndView = new ModelAndView("author");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(authorList)));
        modelAndView.addObject(source);
        return modelAndView;
    }

}
