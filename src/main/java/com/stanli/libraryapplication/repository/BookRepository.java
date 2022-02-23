package com.stanli.libraryapplication.repository;

import com.stanli.libraryapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
