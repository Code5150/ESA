package com.example.l2_1.controller.rest;

import com.example.l2_1.dto.AuthorDTO;
import com.example.l2_1.dto.BookDTO;
import com.example.l2_1.dto.GenreDTO;
import com.example.l2_1.entity.Author;
import com.example.l2_1.entity.Book;
import com.example.l2_1.entity.Genre;
import com.example.l2_1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<BookDTO> getList(){
        return bookService.selectAll().stream().map(b -> new BookDTO(b).withAuthors(
                b.getAuthors().stream().map(AuthorDTO::new).collect(Collectors.toSet())
        ).withGenres(
                b.getGenres().stream().map(GenreDTO::new).collect(Collectors.toSet())
        )).collect(Collectors.toList());
    }


    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public BookDTO getById(@PathVariable String id) {
        var book = bookService.select(UUID.fromString(id));
        return new BookDTO(book).withAuthors(
                book.getAuthors().stream().map(AuthorDTO::new).collect(Collectors.toSet())
        ).withGenres(
                book.getGenres().stream().map(GenreDTO::new).collect(Collectors.toSet())
        );
    }

    @PostMapping("/create")
    public void createBook(@RequestBody BookDTO book) {
        bookService.insert(new Book(book));
    }


    @PatchMapping("/{id}/update")
    public void updateBook(@PathVariable String id, @RequestBody BookDTO book) {
        Book editedBook = bookService.select(UUID.fromString(id));

        if(book.getPrice() != null) {
            editedBook.setPrice(book.getPrice());
        }

        if(book.getName() != null) {
            editedBook.setName(book.getName());
        }

        if(book.getEditionYear() != null) {
            editedBook.setEditionYear(book.getEditionYear());
        }

        if(book.getDescription() != null) {
            editedBook.setDescription(book.getDescription());
        }

        if(book.getGenres() != null) {
            editedBook.setGenres(book.getGenres().stream().map(Genre::new).collect(Collectors.toSet()));
        }

        if(book.getAuthors() != null) {
            editedBook.setAuthors(book.getAuthors().stream().map(Author::new).collect(Collectors.toSet()));
        }

        bookService.update(editedBook);

    }

    @DeleteMapping("/{id}/delete")
    public void deleteById(@PathVariable String id) {
        bookService.delete(UUID.fromString(id));
    }


}
