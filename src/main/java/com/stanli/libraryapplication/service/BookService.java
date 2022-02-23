package com.stanli.libraryapplication.service;

import com.stanli.libraryapplication.dto.BookRequestDto;
import com.stanli.libraryapplication.dto.BookResponseDto;
import com.stanli.libraryapplication.model.Book;

import java.util.*;

public interface BookService {

    BookResponseDto save (BookRequestDto bookRequestDto);

    BookResponseDto editBook (BookRequestDto bookRequestDto);

    List<Book> getAllBooks ();
}
