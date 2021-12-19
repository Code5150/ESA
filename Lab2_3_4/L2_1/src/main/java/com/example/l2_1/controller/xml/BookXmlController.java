package com.example.l2_1.controller.xml;

import com.example.l2_1.entity.Book;
import com.example.l2_1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/xml/book", produces = MediaType.APPLICATION_XML_VALUE)
public class BookXmlController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getList(){
        return bookService.selectAll();
    }


    @GetMapping("/{id}")
    public Book getById(@PathVariable String id) {
        return bookService.select(UUID.fromString(id));
    }

    @PostMapping("/create")
    public void createBook(@RequestBody Book book) {
        bookService.insert(book);
    }


    @PatchMapping("/{id}/update")
    public void updateBook(@PathVariable String id, @RequestBody Book book) {
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
            editedBook.setGenres(book.getGenres());
        }

        if(book.getAuthors() != null) {
            editedBook.setAuthors(book.getAuthors());
        }

        bookService.update(editedBook);

    }

    @DeleteMapping("/{id}/delete")
    public void deleteById(@PathVariable String id) {
        bookService.delete(UUID.fromString(id));
    }

}
