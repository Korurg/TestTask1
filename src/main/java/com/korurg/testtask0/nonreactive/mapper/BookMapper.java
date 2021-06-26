package com.korurg.testtask0.nonreactive.mapper;

import com.korurg.testtask0.nonreactive.data.entity.Book;
import com.korurg.testtask0.nonreactive.web.dto.BookDto;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto apply(@Nullable Book book) {
        if (book == null) {
            return null;
        }

        return BookDto.builder()
                .author(book.getAuthor())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .printYear(book.getPrintYear())
                .readAlready(book.isReadAlready())
                .title(book.getTitle())
                .build();
    }
}
