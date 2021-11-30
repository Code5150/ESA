package com.example.esa_lab2.models;

import com.example.esa_lab2.dto.Author;
import com.example.esa_lab2.dto.Book;

public class BookViewModel {
    private String id;
    private String name;
    private String authors;
    private Integer year;
    private Integer price;

    public BookViewModel(Book book) {
        this.id = book.getId().toString();
        this.name = book.getName();
        this.year = book.getEditionYear().getYear() + 1900;
        this.price = book.getPrice();

        int numAuthors = 2;
        for (Author author : book.getAuthors()) {
            numAuthors--;
            if (numAuthors < 0) {
                this.authors += "...";
                break;
            }
            this.authors += author.getName();
            this.authors += " ";
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthors() {
        return authors;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getPrice() {
        return price;
    }
}
