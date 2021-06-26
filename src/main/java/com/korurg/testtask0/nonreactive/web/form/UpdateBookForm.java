package com.korurg.testtask0.nonreactive.web.form;

import com.korurg.testtask0.nonreactive.web.ValidationError;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@Builder
public class UpdateBookForm {
    @Size(max = 100, message = ValidationError.TOO_LARGE)
    private final String title;

    private final String description;

    @Size(max = 10, message = ValidationError.TOO_LARGE)
    private final String isbn;

    @PositiveOrZero(message = ValidationError.POSITIVE)
    private final int printYear;
}
