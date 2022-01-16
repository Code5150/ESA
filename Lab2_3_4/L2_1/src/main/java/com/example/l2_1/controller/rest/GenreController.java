package com.example.l2_1.controller.rest;

import com.example.l2_1.dto.AuthorDTO;
import com.example.l2_1.dto.BookDTO;
import com.example.l2_1.dto.GenreDTO;
import com.example.l2_1.entity.Genre;
import com.example.l2_1.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<GenreDTO> getList(){
        return genreService.selectAll().stream().map(g -> new GenreDTO(g).withBooks(
              g.getBooks().stream().map(b -> new BookDTO(b).withAuthors(
                      b.getAuthors().stream().map(AuthorDTO::new).collect(Collectors.toSet())
              )).collect(Collectors.toSet())
        )).collect(Collectors.toList());
    }


    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public GenreDTO getById(@PathVariable String id) {
        var genre = genreService.select(UUID.fromString(id));
        return new GenreDTO(genre).withBooks(genre.getBooks().stream().map(b -> new BookDTO(b).withAuthors(
                b.getAuthors().stream().map(AuthorDTO::new).collect(Collectors.toSet())
        )).collect(Collectors.toSet()));
    }

    @PostMapping("/create")
    public void createGenre(@RequestBody GenreDTO genre) {
        genreService.insert(new Genre(genre));
    }


    @PatchMapping("/{id}/update")
    public void updateGenre(@PathVariable String id, @RequestBody GenreDTO genre) {
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
