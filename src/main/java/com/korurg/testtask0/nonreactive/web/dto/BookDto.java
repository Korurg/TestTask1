package com.korurg.testtask0.nonreactive.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private String title;

    private String description;

    private String author;

    private String isbn;

    private int printYear;

    private boolean readAlready;
}
