package com.example.l2_1.controllers.xslt_controllers;

import com.example.l2_1.entity.Genre;
import com.example.l2_1.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.List;

public class GenreXsltController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public ModelAndView getGenres() throws JsonProcessingException {
        List<Genre> genreList = genreService.selectAll();
        ModelAndView modelAndView = new ModelAndView("genre");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(genreList)));
        modelAndView.addObject(source);
        return modelAndView;
    }
}
