package com.korurg.testtask0.nonreactive.service.bookservice;

import com.korurg.testtask0.nonreactive.data.entity.Book;
import com.korurg.testtask0.nonreactive.data.repository.BookRepository;
import com.korurg.testtask0.nonreactive.exception.http.NotFoundException;
import com.korurg.testtask0.nonreactive.service.BookService;
import com.korurg.testtask0.nonreactive.util.StringUtil;
import com.korurg.testtask0.nonreactive.web.form.CreateBookForm;
import com.korurg.testtask0.nonreactive.web.form.UpdateBookForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ContextConfiguration(classes = BookServiceTestConfiguration.class)
class BookServiceDataJpaTest {

    @Autowired
    BookService bookService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    EntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        testEntityManager.persist(
                Book.builder()
                        .author("Elon Mask")
                        .description("Book about android with kotlin")
                        .isbn("12312adasda")
                        .printYear(2020)
                        .title("Android in practice")
                        .build()
        );


        testEntityManager.persist(
                Book.builder()
                        .author("Jim Carrey")
                        .description("Book about spring")
                        .isbn("12312adasda")
                        .printYear(2020)
                        .title("Spring in practice")
                        .build()
        );

        testEntityManager.persist(
                Book.builder()
                        .author("Antonio Banderas")
                        .description("Book about python")
                        .isbn("12312adasda")
                        .printYear(2020)
                        .title("Python for dummies")
                        .build()
        );


        testEntityManager.flush();
    }

    @Test
    void createBook_createBook() {
        CreateBookForm createBookForm = CreateBookForm.builder()
                .author("Jim Carrey")
                .description("About android")
                .isbn("123asda1231")
                .printYear(2020)
                .title("Android")
                .build();


        Book createdBook = bookService.create(createBookForm);

        assertThat(createdBook)
                .isNotNull()
                .matches(
                        book -> book.getAuthor().equals(createBookForm.getAuthor()) &&
                                book.getDescription().equals(createBookForm.getDescription()) &&
                                book.getIsbn().equals(createBookForm.getIsbn()) &&
                                book.getTitle().equals(createBookForm.getTitle()) &&
                                book.getPrintYear() == createBookForm.getPrintYear() &&
                                !book.isReadAlready()
                );
    }


    @Test
    void updateBook_updateIsbn() {
        Book bookInDb = bookRepository.findAll().get(0);

        UpdateBookForm updateBookForm = UpdateBookForm.builder()
                .isbn("asdjlsaj13123")
                .build();

        Book updatedBook = bookService.update(bookInDb.getId(), updateBookForm);

        assertThat(updatedBook)
                .isNotNull()
                .matches(
                        book -> book.getAuthor().equals(bookInDb.getAuthor()) &&
                                book.getDescription().equals(bookInDb.getDescription()) &&
                                book.getIsbn().equals(updateBookForm.getIsbn()) &&
                                book.getTitle().equals(bookInDb.getTitle()) &&
                                book.getPrintYear() == bookInDb.getPrintYear() &&
                                !book.isReadAlready()
                );
    }

    @Test
    void updateBook_updateDescription() {
        Book bookInDb = bookRepository.findAll().get(0);

        UpdateBookForm updateBookForm = UpdateBookForm.builder()
                .description("new description")
                .build();

        Book updatedBook = bookService.update(bookInDb.getId(), updateBookForm);

        assertThat(updatedBook)
                .isNotNull()
                .matches(
                        book -> book.getAuthor().equals(bookInDb.getAuthor()) &&
                                book.getDescription().equals(updateBookForm.getDescription()) &&
                                book.getIsbn().equals(bookInDb.getIsbn()) &&
                                book.getTitle().equals(bookInDb.getTitle()) &&
                                book.getPrintYear() == bookInDb.getPrintYear() &&
                                !book.isReadAlready()
                );
    }

    @Test
    void updateBook_updateTitle() {
        Book bookInDb = bookRepository.findAll().get(0);

        UpdateBookForm updateBookForm = UpdateBookForm.builder()
                .title("new title")
                .build();

        Book updatedBook = bookService.update(bookInDb.getId(), updateBookForm);

        assertThat(updatedBook)
                .isNotNull()
                .matches(
                        book -> book.getAuthor().equals(bookInDb.getAuthor()) &&
                                book.getDescription().equals(bookInDb.getDescription()) &&
                                book.getIsbn().equals(bookInDb.getIsbn()) &&
                                book.getTitle().equals(updateBookForm.getTitle()) &&
                                book.getPrintYear() == bookInDb.getPrintYear() &&
                                !book.isReadAlready()
                );
    }

    @Test
    void updateBook_updatePrintYear() {
        Book bookInDb = bookRepository.findAll().get(0);

        UpdateBookForm updateBookForm = UpdateBookForm.builder()
                .printYear(2007)
                .build();

        Book updatedBook = bookService.update(bookInDb.getId(), updateBookForm);

        assertThat(updatedBook)
                .isNotNull()
                .matches(
                        book -> book.getAuthor().equals(bookInDb.getAuthor()) &&
                                book.getDescription().equals(bookInDb.getDescription()) &&
                                book.getIsbn().equals(bookInDb.getIsbn()) &&
                                book.getTitle().equals(bookInDb.getTitle()) &&
                                book.getPrintYear() == updateBookForm.getPrintYear() &&
                                !book.isReadAlready()
                );
    }

    @Test
    void updateBook_updateBookFieldAndBookNotExists() {
        UpdateBookForm updateBookForm = UpdateBookForm.builder()
                .printYear(2007)
                .build();

        assertThrows(NotFoundException.class, () -> bookService.update(100, updateBookForm));
    }

    @Test
    void updateBook_setIsReadAndBookExists() {
        Book bookInDb = bookRepository.findAll().get(0);

        Book updatedBook = bookService.setIsRead(bookInDb.getId());

        assertThat(updatedBook)
                .isNotNull()
                .matches(
                        book -> book.getAuthor().equals(bookInDb.getAuthor()) &&
                                book.getDescription().equals(bookInDb.getDescription()) &&
                                book.getIsbn().equals(bookInDb.getIsbn()) &&
                                book.getTitle().equals(bookInDb.getTitle()) &&
                                book.getPrintYear() == bookInDb.getPrintYear() &&
                                book.isReadAlready()
                );
    }

    @Test
    void updateBook_setIsReadAndBookNotExists() {
        assertThrows(NotFoundException.class, () -> bookService.setIsRead(1000));
    }

    @Test
    void getBooks_getByPageable() {
        Page<Book> books = bookService.get(PageRequest.of(1, 2));

        assertThat(books)
                .hasSize(1);
    }

    @Test
    void getBook_getByIdAndBookExists() {
        Book bookInDb = bookRepository.findAll().get(0);

        Book foundBook = bookService.get(bookInDb.getId());

        assertThat(foundBook)
                .isNotNull()
                .matches(
                        book -> book.getAuthor().equals(bookInDb.getAuthor()) &&
                                book.getDescription().equals(bookInDb.getDescription()) &&
                                book.getIsbn().equals(bookInDb.getIsbn()) &&
                                book.getTitle().equals(bookInDb.getTitle()) &&
                                book.getPrintYear() == bookInDb.getPrintYear() &&
                                book.isReadAlready() == bookInDb.isReadAlready()
                );
    }

    @Test
    void getBook_getByIdAndBookNotExists() {
        assertThrows(NotFoundException.class, () -> bookService.get(100));
    }


    @Test
    void getBook_getBookByPhrase() {
        String phraseForSearch = "android";
        Page<Book> foundBook = bookService.getByPhrase(phraseForSearch, Pageable.unpaged());

        assertThat(foundBook)
                .isNotEmpty()
                .allMatch(
                        book -> StringUtil.containsIgnoreCase(book.getDescription(), phraseForSearch) ||
                                StringUtil.containsIgnoreCase(book.getTitle(), phraseForSearch)
                );
    }

}
