package com.stanli.libraryapplication.dto;

import com.stanli.libraryapplication.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private String bookTitle;
    private String bookAuthor;
    private Category categoryOfBooks;

}
