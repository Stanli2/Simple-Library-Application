package com.stanli.libraryapplication.service.serviceImplementation;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.stanli.libraryapplication.dto.AddToCategoryDto;
import com.stanli.libraryapplication.dto.CategoryRequestDto;
import com.stanli.libraryapplication.model.Book;
import com.stanli.libraryapplication.model.Category;
import com.stanli.libraryapplication.repository.BookRepository;
import com.stanli.libraryapplication.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Test
    void testAddCategory() {
        Category category = new Category();
        category.setCategoryOfBooks("Science");
        category.setId(123L);
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setCategoryOfBook("Science");
        assertEquals("Science", this.categoryServiceImpl.addCategory(categoryRequestDto).getCategoryOfBook());
        verify(this.categoryRepository).save((Category) any());
    }
    @Test
    void testEditCategory() {
        Category category = new Category();
        category.setCategoryOfBooks("Science");
        category.setId(123L);
        Category category1 = new Category();
        category1.setCategoryOfBooks("Drama");
        category1.setId(124L);
        when(this.categoryRepository.save((Category) any())).thenReturn(category1);
        when(this.categoryRepository.getById((Long) any())).thenReturn(category);
        CategoryRequestDto categoryRequestDto = new CategoryRequestDto();
        categoryRequestDto.setCategoryOfBook("Fantasy");
        category1.setCategoryOfBooks(categoryRequestDto.getCategoryOfBook());
        assertEquals("Fantasy",
                this.categoryServiceImpl.editCategory(124L, categoryRequestDto).getCategoryOfBook());
        verify(this.categoryRepository).getById((Long) any());
        verify(this.categoryRepository).save((Category) any());
    }
    @Test
    void testDeleteCategory() {
        doNothing().when(this.categoryRepository).deleteById((Long) any());
        assertEquals("Category Deleted", this.categoryServiceImpl.deleteCategory(123L));
        verify(this.categoryRepository).deleteById((Long) any());
        assertTrue(this.categoryServiceImpl.getAllCategory().isEmpty());
    }
    @Test
    void testGetAllCategory() {
        ArrayList<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setCategoryOfBooks("Science");
        category.setId(123L);
        Category category1 = new Category();
        category1.setCategoryOfBooks("Drama");
        category1.setId(124L);
        categoryList.add(category);
        categoryList.add(category1);
        when(this.categoryRepository.findAll()).thenReturn(categoryList);
        List<Category> actualAllCategory = this.categoryServiceImpl.getAllCategory();
        assertSame(categoryList, actualAllCategory);
        assertFalse(actualAllCategory.isEmpty());
        verify(this.categoryRepository).findAll();
    }
    @Test
    void testAddBookToCategory() {
        Category category = new Category();
        category.setCategoryOfBooks("Science");
        category.setId(123L);
        Optional<Category> ofResult = Optional.of(category);
        when(this.categoryRepository.findByCategoryOfBooks((String) any())).thenReturn(ofResult);
        Category category1 = new Category();
        category1.setCategoryOfBooks("Drama");
        category1.setId(124L);
        Book book = new Book();
        book.setBookAuthor("Elon Musk");
        book.setBookTitle("Theory of Relativity");
        book.setCategoryOfBooks(category);
        book.setId(123L);
        Optional<Book> ofResult1 = Optional.of(book);
        Category category2 = new Category();
        category2.setCategoryOfBooks("Category Of Books");
        category2.setId(123L);
        Book book1 = new Book();
        book1.setBookAuthor("Chinua Achebe");
        book1.setBookTitle("Things Fall Apart");
        book1.setCategoryOfBooks(category);
        book1.setId(125L);
        when(this.bookRepository.save((Book) any())).thenReturn(book1);
        when(this.bookRepository.findByBookTitle((String) any())).thenReturn(ofResult1);
        assertEquals("Book added to the category",
                this.categoryServiceImpl.addBookToCategory(new AddToCategoryDto("Fantasy", "Escapades")));
        verify(this.categoryRepository).findByCategoryOfBooks((String) any());
        verify(this.bookRepository).findByBookTitle((String) any());
        verify(this.bookRepository).save((Book) any());
        assertTrue(this.categoryServiceImpl.getAllCategory().isEmpty());
    }
}