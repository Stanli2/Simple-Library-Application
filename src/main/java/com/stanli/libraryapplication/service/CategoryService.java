package com.stanli.libraryapplication.service;

import com.stanli.libraryapplication.dto.AddToCategoryDto;
import com.stanli.libraryapplication.dto.BookRequestDto;
import com.stanli.libraryapplication.dto.CategoryRequestDto;
import com.stanli.libraryapplication.dto.CategoryResponseDto;
import com.stanli.libraryapplication.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {

    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto editCategory(Long id, CategoryRequestDto categoryRequestDto);

    String deleteCategory(Long id);

    String addBookToCategory(AddToCategoryDto addToCategoryDto);

    List<Category> getAllCategory();


}
