package com.korurg.testtask0.nonreactive.data.repository;

import com.korurg.testtask0.nonreactive.data.entity.Book;
import com.korurg.testtask0.nonreactive.util.StringUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryDataJpaTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestEntityManager testEntityManager;

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
                        .description("Book AbOuT spring")
                        .isbn("12312adasda")
                        .printYear(2020)
                        .title("Spring in practice")
                        .build()
        );

        testEntityManager.persist(
                Book.builder()
                        .author("Antonio Banderas")
                        .description("Book python")
                        .isbn("12312adasda")
                        .printYear(2020)
                        .title("Python for dummies AbOuT")
                        .build()
        );



        testEntityManager.flush();
    }


    @Test
    void findBooksByPhraseInTitleAndDescription_findBookWithPhraseAbout() {
        var phrase = "about";
        var books = bookRepository.findByPhrase(phrase, Pageable.unpaged());

        Assertions.assertThat(books)
                .isNotEmpty()
                .allMatch(book -> StringUtil.containsIgnoreCase(book.getDescription(), phrase)
                        || StringUtil.containsIgnoreCase(book.getTitle(), phrase));
    }

    @Test
    void findBooksByPhraseInTitle_findBookWithPhraseDummies() {
        var phrase = "dummies";
        var books = bookRepository.findByPhrase(phrase, Pageable.unpaged());

        Assertions.assertThat(books)
                .isNotEmpty()
                .allMatch(book -> StringUtil.containsIgnoreCase(book.getTitle(), phrase));
    }

    @Test
    void findBooksByPhraseInDescription_findBookWithPhraseKotlin() {
        var phrase = "kotlin";
        var books = bookRepository.findByPhrase(phrase, Pageable.unpaged());

        Assertions.assertThat(books)
                .isNotEmpty()
                .allMatch(book -> StringUtil.containsIgnoreCase(book.getDescription(), phrase));
    }

    @Test
    void findBooksByPhraseInTitleAndDescriptionAndUsePage_findBookWithPhraseAboutAndSelectSecondPageWithSizeTwo() {
        var phrase = "about";
        var books = bookRepository.findByPhrase(phrase, PageRequest.of(1, 2));

        Assertions.assertThat(books)
                .hasSize(1)
                .allMatch(book -> StringUtil.containsIgnoreCase(book.getDescription(), phrase)
                        || StringUtil.containsIgnoreCase(book.getTitle(), phrase));
    }


}
