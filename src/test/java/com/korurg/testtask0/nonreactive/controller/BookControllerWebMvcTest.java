package com.korurg.testtask0.nonreactive.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korurg.testtask0.nonreactive.data.entity.Book;
import com.korurg.testtask0.nonreactive.data.repository.BookRepository;
import com.korurg.testtask0.nonreactive.exception.http.NotFoundException;
import com.korurg.testtask0.nonreactive.service.BookService;
import com.korurg.testtask0.nonreactive.web.form.CreateBookForm;
import com.korurg.testtask0.nonreactive.web.form.UpdateBookForm;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {WebMvcTestConfiguration.class})
class BookControllerWebMvcTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    List<Book> booksInDb;


    @BeforeEach
    void setUp() {
        booksInDb = Arrays.asList(
                Book.builder()
                        .author("Antonio Banderas")
                        .description("Book about python")
                        .isbn("12312adasda")
                        .printYear(2020)
                        .title("Python for dummies")
                        .build(),
                Book.builder()
                        .author("Jim Carrey")
                        .description("Book about spring")
                        .isbn("12312adasda")
                        .printYear(2020)
                        .title("Spring in practice")
                        .build(),
                Book.builder()
                        .author("Elon Mask")
                        .description("Book about android with kotlin")
                        .isbn("12312adasda")
                        .printYear(2020)
                        .title("Android in practice")
                        .build());
    }

    @Test
    @SneakyThrows
    void getBooks_getByPageable() {
        Page<Book> pageWithBooksInDb = new PageImpl(booksInDb);
        when(bookService.get(Mockito.any(Pageable.class))).thenReturn(pageWithBooksInDb);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*]", hasSize(3)));
    }

    @Test
    @SneakyThrows
    void getBookInfo_getByIdAndBookExists() {
        when(bookService.get(1)).thenReturn(booksInDb.get(0));

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", hasToString(booksInDb.get(0).getTitle())));
    }

    @Test
    @SneakyThrows
    void getBookInfo_getByIdAndBookNotExists() {
        when(bookService.get(1)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void createBook() {
        CreateBookForm createBookForm = CreateBookForm.builder()
                .title("New book")
                .description("New created book")
                .author("New author")
                .isbn("mljkl32131")
                .printYear(2021)
                .build();

        mockMvc.perform(
                post("/books/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createBookForm)))
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    void changeBookIsRead_bookExists() {
        when(bookService.setIsRead(1))
                .thenReturn(Book.builder()
                        .readAlready(true)
                        .build());

        mockMvc.perform(
                post("/books/1/is-read"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.readAlready", is(true)));
    }

    @Test
    @SneakyThrows
    void changeBookIsRead_bookNotExists() {
        when(bookService.setIsRead(1)).thenThrow(NotFoundException.class);

        mockMvc.perform(
                post("/books/1/is-read"))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void updateBook_bookExists() {
        UpdateBookForm updateBookForm = UpdateBookForm.builder()
                .build();

        when(bookService.update(1, updateBookForm)).thenReturn(Book.builder().build());

        mockMvc.perform(
                post("/books/1/update")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updateBookForm)))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void updateBook_bookNotExists() {
        UpdateBookForm updateBookForm = UpdateBookForm.builder()
                .build();

        when(bookService.update(1, updateBookForm)).thenThrow(NotFoundException.class);

        mockMvc.perform(
                post("/books/1/update")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updateBookForm)))
                .andExpect(status().isNotFound());
    }


}
