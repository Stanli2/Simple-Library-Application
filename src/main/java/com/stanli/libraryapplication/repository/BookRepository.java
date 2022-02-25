package com.stanli.libraryapplication.repository;

import com.stanli.libraryapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByBookTitle(String bookTitle);
}

