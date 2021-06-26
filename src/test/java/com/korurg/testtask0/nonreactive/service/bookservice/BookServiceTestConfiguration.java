package com.korurg.testtask0.nonreactive.service.bookservice;

import com.korurg.testtask0.nonreactive.data.repository.BookRepository;
import com.korurg.testtask0.nonreactive.service.BookService;
import com.korurg.testtask0.nonreactive.service.impementation.BookServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class BookServiceTestConfiguration {

    @Bean
    BookService bookService(BookRepository bookRepository) {
        return new BookServiceImpl(bookRepository);
    }

}
