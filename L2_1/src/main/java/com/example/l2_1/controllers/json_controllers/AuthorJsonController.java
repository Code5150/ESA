package com.example.l2_1.controllers.json_controllers;

import com.example.l2_1.entities.Author;
import com.example.l2_1.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/json/author", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorJsonController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getList(){
        return authorService.selectAll();
    }


    @GetMapping("/{id}")
    public Author getById(@PathVariable String id) {
        return authorService.select(UUID.fromString(id));
    }

    @PostMapping("/create")
    public void createAuthor(@RequestBody Author author) {
        authorService.insert(author);
    }


    @PatchMapping("/{id}/update")
    public void updateAuthor(@PathVariable String id, @RequestBody Author author) {
        Author editedAuthor = authorService.select(UUID.fromString(id));

        if(author.getBio() != null) {
            editedAuthor.setBio(author.getBio());
        }

        if(author.getName() != null) {
            editedAuthor.setName(author.getName());
        }

        authorService.update(editedAuthor);

    }

    @DeleteMapping("/{id}/delete")
    public void deleteById(@PathVariable String id) {
        authorService.delete(UUID.fromString(id));
    }

}
