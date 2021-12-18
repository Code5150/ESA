package com.example.l2_1.controllers.xml_controllers;

import com.example.l2_1.entities.Genre;
import com.example.l2_1.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/xml/genre", produces = MediaType.APPLICATION_XML_VALUE)
public class GenreXmlController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<Genre> getList(){
        return genreService.selectAll();
    }


    @GetMapping("/{id}")
    public Genre getById(@PathVariable String id) {
        return genreService.select(UUID.fromString(id));
    }

    @PostMapping("/create")
    public void createGenre(@RequestBody Genre genre) {
        genreService.insert(genre);
    }


    @PatchMapping("/{id}/update")
    public void updateGenre(@PathVariable String id, @RequestBody Genre genre) {
        Genre editedGenre = genreService.select(UUID.fromString(id));

        if(genre.getName() != null) {
            editedGenre.setName(genre.getName());
        }

        genreService.update(editedGenre);

    }

    @DeleteMapping("/{id}/delete")
    public void deleteById(@PathVariable String id) {
        genreService.delete(UUID.fromString(id));
    }

}
