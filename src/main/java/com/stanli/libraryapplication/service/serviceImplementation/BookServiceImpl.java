package com.stanli.libraryapplication.service.serviceImplementation;

import com.stanli.libraryapplication.dto.BookRequestDto;
import com.stanli.libraryapplication.dto.BookResponseDto;
import com.stanli.libraryapplication.model.Book;
import com.stanli.libraryapplication.repository.BookRepository;
import com.stanli.libraryapplication.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponseDto save(BookRequestDto bookRequestDto) {
        return null;
    }

    @Override
    public BookResponseDto editBook(BookRequestDto bookRequestDto) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return null;
    }
}
