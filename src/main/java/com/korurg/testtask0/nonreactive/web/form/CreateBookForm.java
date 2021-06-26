package com.korurg.testtask0.nonreactive.web.form;

import com.korurg.testtask0.nonreactive.web.ValidationError;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@Builder
public class CreateBookForm {
    @NotBlank(message = ValidationError.BLANK)
    @Size(max = 100, message = ValidationError.TOO_LARGE)
    private final String title;

    @NotBlank(message = ValidationError.BLANK)
    private final String description;

    @NotBlank(message = ValidationError.BLANK)
    @Size(max = 100, message = ValidationError.TOO_LARGE)
    private final String author;

    @NotBlank(message = ValidationError.BLANK)
    @Size(max = 10, message = ValidationError.TOO_LARGE)
    private final String isbn;

    @Positive(message = ValidationError.POSITIVE)
    private final int printYear;
}
