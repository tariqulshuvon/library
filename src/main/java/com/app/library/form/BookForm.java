package com.app.library.form;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookForm {

    private Long id;

    @NotNull(message = "Please Select any Category")
    private long categoryId;

    @NotBlank(message = "Please Give a Name for the Book")
    private String name;

    private String author;

    @Size(min = 10, message = "Please mention Release Date of the book")
    private String releaseDate;
}
