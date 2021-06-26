package com.korurg.testtask0.nonreactive.service;

import com.korurg.testtask0.nonreactive.data.entity.Book;
import com.korurg.testtask0.nonreactive.web.form.CreateBookForm;
import com.korurg.testtask0.nonreactive.web.form.UpdateBookForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Book create(CreateBookForm bookForm);

    Book update(long id, UpdateBookForm bookForm);

    Book setIsRead(long id);

    Page<Book> get(Pageable pageable);

    Book get(long id);

    Page<Book> getByPhrase(String phrase, Pageable pageable);
}
