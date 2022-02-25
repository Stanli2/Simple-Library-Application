package com.stanli.libraryapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCategoryDto {

    private String categoryOfBooks;
    private String bookTitle;
}
