package com.stanli.libraryapplication.service;

import com.stanli.libraryapplication.dto.BookRequestDto;
import com.stanli.libraryapplication.dto.BookResponseDto;
import com.stanli.libraryapplication.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface BookService {

    BookResponseDto save (BookRequestDto bookRequestDto);

    BookResponseDto editBook (Long id, BookRequestDto bookRequestDto);

    String deleteBook (Long id);

    List<Book> getAllBooks ();
}
