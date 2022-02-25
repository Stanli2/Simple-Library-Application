package com.stanli.libraryapplication.service.serviceImplementation;

import com.stanli.libraryapplication.dto.AddToCategoryDto;
import com.stanli.libraryapplication.dto.CategoryRequestDto;
import com.stanli.libraryapplication.dto.CategoryResponseDto;
import com.stanli.libraryapplication.model.Book;
import com.stanli.libraryapplication.model.Category;
import com.stanli.libraryapplication.repository.BookRepository;
import com.stanli.libraryapplication.repository.CategoryRepository;
import com.stanli.libraryapplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto) {
        if (categoryRequestDto != null) {

            Category category = new Category();

            category.setCategoryOfBooks(categoryRequestDto.getCategoryOfBook());

            Category category1 = categoryRepository.save(category);
            CategoryResponseDto categoryResponseDto = new CategoryResponseDto(category1.getCategoryOfBooks());

            return categoryResponseDto;
        }
        return null;
    }

    @Override
    public CategoryResponseDto editCategory(Long id, CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.getById(id);

        category.setCategoryOfBooks(categoryRequestDto.getCategoryOfBook());

        Category category1 = categoryRepository.save(category);
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto(category1.getCategoryOfBooks());

        return categoryResponseDto;
    }

    @Override
    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Category Deleted";
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public String addBookToCategory(AddToCategoryDto addToCategoryDto) {
        Optional<Book> book = bookRepository.findByBookTitle(addToCategoryDto.getBookTitle());
        Optional<Category> category = categoryRepository.findByCategoryOfBooks(addToCategoryDto.getCategoryOfBooks());

        book.get().setCategoryOfBooks(category.get());
        bookRepository.save(book.get());
        return "Book added to the category";
    }
}
