package com.stanli.libraryapplication.dto;

import com.stanli.libraryapplication.model.Category;
import lombok.Data;

@Data
public class BookRequestDto {

    private String bookTitle;
    private String bookAuthor;
    private Category category;
}
