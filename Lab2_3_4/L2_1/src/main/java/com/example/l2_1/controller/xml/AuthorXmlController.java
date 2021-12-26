package com.example.l2_1.controller.xml;

import com.example.l2_1.dto.AuthorDTO;
import com.example.l2_1.dto.BookDTO;
import com.example.l2_1.dto.GenreDTO;
import com.example.l2_1.entity.Author;
import com.example.l2_1.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/xml/author", produces = MediaType.APPLICATION_XML_VALUE)
public class AuthorXmlController {


    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<AuthorDTO> getList(){
        return authorService.selectAll().stream().map(a -> new AuthorDTO(a).withBooks(
                a.getBooks().stream().map(b -> new BookDTO(b).withGenres(
                        b.getGenres().stream().map(GenreDTO::new).collect(Collectors.toSet())
                )).collect(Collectors.toSet())
        )).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public AuthorDTO getById(@PathVariable String id) {
        var author = authorService.select(UUID.fromString(id));
        return new AuthorDTO(author).withBooks(
                author.getBooks().stream().map(b -> new BookDTO(b).withGenres(
                        b.getGenres().stream().map(GenreDTO::new).collect(Collectors.toSet())
                )).collect(Collectors.toSet())
        );
    }

    @PostMapping("/create")
    public void createAuthor(@RequestBody AuthorDTO author) {
        authorService.insert(new Author(author));
    }


    @PatchMapping("/{id}/update")
    public void updateAuthor(@PathVariable String id, @RequestBody AuthorDTO author) {
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
