package com.stanli.libraryapplication.repository;

import com.stanli.libraryapplication.dto.AddToCategoryDto;
import com.stanli.libraryapplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryOfBooks(String categoryOfBooks);
}
