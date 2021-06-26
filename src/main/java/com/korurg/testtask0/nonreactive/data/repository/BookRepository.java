package com.korurg.testtask0.nonreactive.data.repository;

import com.korurg.testtask0.nonreactive.data.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select book from Book book where lower(book.description) like concat('%',lower(:phrase) ,'%') or lower(book.title) like concat('%',lower(:phrase) ,'%')")
    Page<Book> findByPhrase(@Param("phrase") String phrase, Pageable pageable);
}
