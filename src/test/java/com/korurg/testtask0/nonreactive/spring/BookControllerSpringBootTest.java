package com.korurg.testtask0.nonreactive.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korurg.testtask0.nonreactive.data.repository.BookRepository;
import com.korurg.testtask0.nonreactive.service.BookService;
import com.korurg.testtask0.nonreactive.web.dto.BookDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@RunWith(SpringRunner.class)
@Sql("classpath:db/test/test.sql")
public class BookControllerSpringBootTest {

    @LocalServerPort
    int port;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Autowired
    TestRestTemplate restTemplate;


    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:13-alpine")
            .withDatabaseName("integration-tests-db")
            .withUsername("postgres")
            .withPassword("postgres");


    @Test
    public void getAllBooks() {


        ParameterizedTypeReference<RestPageImpl<BookDto>> responseType = new ParameterizedTypeReference<RestPageImpl<BookDto>>() {
        };
        var jsonResult = restTemplate.exchange("http://localhost:" + port + "/books",
                HttpMethod.GET,
                null,
                responseType);

    }
}
