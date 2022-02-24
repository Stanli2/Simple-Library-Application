package com.stanli.libraryapplication.service.serviceImplementation;

import com.stanli.libraryapplication.dto.BookRequestDto;
import com.stanli.libraryapplication.dto.BookResponseDto;
import com.stanli.libraryapplication.model.Book;
import com.stanli.libraryapplication.repository.BookRepository;
import com.stanli.libraryapplication.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponseDto save(BookRequestDto bookRequestDto) {
        if (bookRequestDto != null){
            Book book = new Book();

            book.setBookTitle(bookRequestDto.getBookTitle());
            book.setBookAuthor(bookRequestDto.getBookAuthor());
            book.setCategory(bookRequestDto.getCategory());

            Book book1 = bookRepository.save(book);
            BookResponseDto bookResponseDto = new BookResponseDto(book1.getBookTitle(), book1.getBookAuthor(), book1.getCategory());

            return bookResponseDto;
        }
        return null;
    }

    @Override
    public BookResponseDto editBook(Long id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.getById(id);

        book.setBookTitle(bookRequestDto.getBookTitle());
        book.setBookAuthor(bookRequestDto.getBookAuthor());
        book.setCategory(bookRequestDto.getCategory());

        Book book1 = bookRepository.save(book);
        BookResponseDto bookResponseDto = new BookResponseDto(book1.getBookTitle(), book1.getBookAuthor(), book1.getCategory());
        return bookResponseDto;
    }

    @Override
    public String deleteBook(Long id) {
        bookRepository.deleteById(id);
        return "Book Deleted";
    }

    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }
}
