package com.korurg.testtask0.nonreactive.data.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table
public class Book extends BaseEntity {
    @Column(length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String author;

    @Column(length = 20)
    private String isbn;

    @Column
    private int printYear;

    @Column
    private boolean readAlready;


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", printYear=" + printYear +
                ", readAlready=" + readAlready +
                '}';
    }
}
