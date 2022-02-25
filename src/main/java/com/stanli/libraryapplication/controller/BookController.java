package com.stanli.libraryapplication.controller;

import com.stanli.libraryapplication.dto.BookRequestDto;
import com.stanli.libraryapplication.dto.BookResponseDto;
import com.stanli.libraryapplication.model.Book;
import com.stanli.libraryapplication.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping("/add")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity.ok().body(bookService.save(bookRequestDto));
    }

    @PutMapping("/editbook/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable (name = "id") Long bookId, @RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity.ok().body(bookService.editBook(bookId, bookRequestDto));
    }

    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable (name = "id") Long bookId) {
        return ResponseEntity.ok().body(bookService.deleteBook(bookId));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }
}
