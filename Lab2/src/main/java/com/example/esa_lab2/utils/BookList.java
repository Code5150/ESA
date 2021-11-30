package com.example.esa_lab2.utils;


import com.example.esa_lab2.dto.Book;
import com.example.esa_lab2.models.BookViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookList {

    private List<BookViewModel> bookViewModelList;

    public BookList(List<Book> books) {
        bookViewModelList = new ArrayList<>();

        for(Book book: books){
            BookViewModel bookViewModel = new BookViewModel(book);
            bookViewModelList.add(bookViewModel);
        }
    }

    public List<BookViewModel> getBookViewModelList() {
        return bookViewModelList;
    }
}
