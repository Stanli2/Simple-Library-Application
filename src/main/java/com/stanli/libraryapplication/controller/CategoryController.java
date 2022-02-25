package com.stanli.libraryapplication.controller;

import com.stanli.libraryapplication.dto.AddToCategoryDto;
import com.stanli.libraryapplication.dto.BookRequestDto;
import com.stanli.libraryapplication.dto.CategoryRequestDto;
import com.stanli.libraryapplication.dto.CategoryResponseDto;
import com.stanli.libraryapplication.model.Category;
import com.stanli.libraryapplication.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;



    @PostMapping("/addcategory")
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok().body(categoryService.addCategory(categoryRequestDto));

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable(name = "id") Long categoryId, @RequestBody CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok().body(categoryService.editCategory(categoryId, categoryRequestDto));
    }

    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") Long categoryId) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(categoryId));
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok().body(categoryService.getAllCategory());
    }

    @PutMapping("/addtocategory")
    public ResponseEntity<String> addBookToCategory(@RequestBody AddToCategoryDto addToCategoryDto){
        return ResponseEntity.ok().body(categoryService.addBookToCategory(addToCategoryDto));
    }
}
