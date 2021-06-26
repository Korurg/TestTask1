package com.korurg.testtask0.nonreactive.web.controller;

import com.korurg.testtask0.nonreactive.mapper.BookMapper;
import com.korurg.testtask0.nonreactive.service.BookService;
import com.korurg.testtask0.nonreactive.web.dto.BookDto;
import com.korurg.testtask0.nonreactive.web.form.CreateBookForm;
import com.korurg.testtask0.nonreactive.web.form.UpdateBookForm;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(BookController.ROOT_URL)
public class BookController {

    static final String ROOT_URL = "books";
    private static final String CREATE_BOOK_URL = "create";
    private static final String UPDATE_BOOK_URL = "{id}/update";
    private static final String CHANGE_STATUS_BOOK_IS_READ_URL = "{id}/is-read";
    private static final String GET_BOOKS_URL = "";
    private static final String GET_BOOK_INFO_URL = "{id}";
    private static final String FIND_BOOK_BY_PHRASE_URL = "find";

    private final BookService bookService;
    private final BookMapper bookMapper;

    @PostMapping(CREATE_BOOK_URL)
    @ApiOperation("Create book")
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody CreateBookForm bookForm) {
        return new ResponseEntity<>(bookMapper.apply(bookService.create(bookForm)), HttpStatus.CREATED);
    }

    @PostMapping(UPDATE_BOOK_URL)
    public BookDto updateBook(@PathVariable long id, @Valid @RequestBody UpdateBookForm bookForm) {
        return bookMapper.apply(bookService.update(id, bookForm));
    }

    @PostMapping(CHANGE_STATUS_BOOK_IS_READ_URL)
    public BookDto changeStatusBookIsRead(@PathVariable long id) {
        return bookMapper.apply(bookService.setIsRead(id));
    }

    @GetMapping(GET_BOOKS_URL)
    public Page<BookDto> getBooks(Pageable pageable) {
        return bookService.get(pageable)
                .map(bookMapper::apply);
    }

    @GetMapping(GET_BOOK_INFO_URL)
    public BookDto getBookInfo(@PathVariable long id) {
        return bookMapper.apply(bookService.get(id));
    }

    @GetMapping(FIND_BOOK_BY_PHRASE_URL)
    public Page<BookDto> findBooksByPhrase(@RequestParam String phrase, Pageable pageable) {
        return bookService.getByPhrase(phrase, pageable).map(bookMapper::apply);
    }

}
