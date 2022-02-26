package com.stanli.libraryapplication.service.serviceImplementation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.stanli.libraryapplication.dto.BookRequestDto;
import com.stanli.libraryapplication.dto.BookResponseDto;
import com.stanli.libraryapplication.model.Book;
import com.stanli.libraryapplication.model.Category;
import com.stanli.libraryapplication.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
@ContextConfiguration(classes = {BookServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookServiceImplTest {
    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookServiceImpl bookServiceImpl;
    @BeforeEach
    void setup() {
    }
    @Test
    void testSave() {
        Category category = new Category();
        category.setCategoryOfBooks("Science");
        category.setId(123L);
        Book book = new Book();
        book.setBookAuthor("Elon Musk");
        book.setBookTitle("Theory of Relativity");
        book.setCategoryOfBooks(category);
        book.setId(123L);
        when(this.bookRepository.save((Book) any())).thenReturn(book);
        Category category1 = new Category();
        category1.setCategoryOfBooks("Drama");
        category1.setId(123L);
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setBookAuthor("Elon Musk");
        bookRequestDto.setBookTitle("Theory of Relativity");
        bookRequestDto.setCategoryOfBooks(category1);
        BookResponseDto actualSaveResult = this.bookServiceImpl.save(bookRequestDto);
        assertEquals("Elon Musk", actualSaveResult.getBookAuthor());
        assertEquals(category, actualSaveResult.getCategoryOfBooks());
        assertEquals("Theory of Relativity", actualSaveResult.getBookTitle());
        verify(this.bookRepository).save((Book) any());
        assertTrue(this.bookServiceImpl.getAllBooks().isEmpty());
    }
    @Test
    void testEditBook() {
        Category category = new Category();
        category.setCategoryOfBooks("Science");
        category.setId(123L);
        Book book = new Book();
        book.setBookAuthor("Elon Musk");
        book.setBookTitle("Theory of Relativity");
        book.setCategoryOfBooks(category);
        book.setId(123L);
        Category category1 = new Category();
        category1.setCategoryOfBooks("Drama");
        category1.setId(123L);
        Book book1 = new Book();
        book1.setBookAuthor("Chinua Achebe");
        book1.setBookTitle("Things Fall Apart");
        book1.setCategoryOfBooks(category);
        book1.setId(125L);
        when(this.bookRepository.save((Book) any())).thenReturn(book1);
        when(this.bookRepository.getById((Long) any())).thenReturn(book);
        Category category2 = new Category();
        category2.setCategoryOfBooks("Fantasy");
        category2.setId(128L);
        BookRequestDto bookRequestDto = new BookRequestDto();
        bookRequestDto.setBookAuthor("Enoch David");
        bookRequestDto.setBookTitle("Escapades");
        bookRequestDto.setCategoryOfBooks(category2);
        BookResponseDto actualEditBookResult = this.bookServiceImpl.editBook(123L, bookRequestDto);
        actualEditBookResult.setBookAuthor("Enoch David");
        actualEditBookResult.setBookTitle("Escapades");
        actualEditBookResult.setCategoryOfBooks(category2);
        assertEquals("Enoch David", actualEditBookResult.getBookAuthor());
        assertEquals(category2, actualEditBookResult.getCategoryOfBooks());
        assertEquals("Escapades", actualEditBookResult.getBookTitle());
        verify(this.bookRepository).getById((Long) any());
        verify(this.bookRepository).save((Book) any());
        assertTrue(this.bookServiceImpl.getAllBooks().isEmpty());
    }
    @Test
    void testDeleteBook() {
        doNothing().when(this.bookRepository).deleteById((Long) any());
        assertEquals("Book Deleted", this.bookServiceImpl.deleteBook(123L));
        verify(this.bookRepository).deleteById((Long) any());
        assertTrue(this.bookServiceImpl.getAllBooks().isEmpty());
    }
    @Test
    void testGetAllBooks() {
        Category category;
        category = new Category();
        category.setCategoryOfBooks("Sciences");
        category.setId(123L);
        Book book = new Book();
        book.setBookAuthor("Albert Einstein");
        book.setBookTitle("Theory of Relativity");
        book.setCategoryOfBooks(category);
        book.setId(124L);
        Book book1 = new Book();
        book1.setBookAuthor("Chinua Achebe");
        book1.setBookTitle("Things Fall Apart");
        book1.setCategoryOfBooks(category);
        book1.setId(125L);
        ArrayList<Book> bookList = new ArrayList<>();
        bookList.add(book);
        bookList.add(book1);
        when(this.bookRepository.findAll()).thenReturn(bookList);
        List<Book> actualAllBooks = this.bookServiceImpl.getAllBooks();
        assertSame(bookList, actualAllBooks);
        assertTrue(actualAllBooks.contains(book));
        verify(this.bookRepository).findAll();
    }
}