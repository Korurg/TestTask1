package com.korurg.testtask0.nonreactive.service.impementation;

import com.korurg.testtask0.nonreactive.data.entity.Book;
import com.korurg.testtask0.nonreactive.data.repository.BookRepository;
import com.korurg.testtask0.nonreactive.exception.http.NotFoundException;
import com.korurg.testtask0.nonreactive.service.BookService;
import com.korurg.testtask0.nonreactive.web.form.CreateBookForm;
import com.korurg.testtask0.nonreactive.web.form.UpdateBookForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book create(CreateBookForm bookForm) {
        return bookRepository.save(Book.builder()
                .author(bookForm.getAuthor())
                .title(bookForm.getTitle())
                .isbn(bookForm.getIsbn())
                .printYear(bookForm.getPrintYear())
                .description(bookForm.getDescription())
                .build());
    }

    @Override
    public Book update(long id, UpdateBookForm bookForm) {
        var book = bookRepository.findById(id).orElseThrow(NotFoundException::new);

        var updatedBook = Book.builder()
                .author(book.getAuthor())
                .description(bookForm.getDescription() == null ? book.getDescription() : bookForm.getDescription())
                .isbn(bookForm.getIsbn() == null ? book.getIsbn() : bookForm.getIsbn())
                .printYear(bookForm.getPrintYear() == 0 ? book.getPrintYear() : bookForm.getPrintYear())
                .title(bookForm.getTitle() == null ? book.getTitle() : bookForm.getTitle())
                .readAlready(false)
                .build();

        return bookRepository.save(updatedBook);
    }

    @Override
    public Book setIsRead(long id) {
        var book = bookRepository.findById(id).orElseThrow(NotFoundException::new);

        var updatedBook = Book.builder()
                .description(book.getDescription())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .printYear(book.getPrintYear())
                .title(book.getTitle())
                .readAlready(true)
                .build();

        return bookRepository.save(updatedBook);
    }

    @Override
    public Page<Book> get(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book get(long id) {
        return bookRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Page<Book> getByPhrase(String phrase, Pageable pageable) {
        return bookRepository.findByPhrase(phrase, pageable);
    }


}
