package com.korurg.testtask0.nonreactive.controller;

import com.korurg.testtask0.nonreactive.data.repository.BookRepository;
import com.korurg.testtask0.nonreactive.mapper.BookMapper;
import com.korurg.testtask0.nonreactive.service.BookService;
import com.korurg.testtask0.nonreactive.service.impementation.BookServiceImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class WebMvcTestConfiguration {

    @MockBean
    BookRepository bookRepository;

    @Bean
    public BookService bookService(BookRepository bookRepository){
        return new BookServiceImpl(bookRepository);
    }


    @Bean
    public BookMapper bookMapper(){
        return new BookMapper();
    }

}
