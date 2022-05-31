package com.app.library.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryForm {

    private Long id;

    @NotBlank(message = " You Must enter The Name of Category")
    private String name;

    private String description;

    private Integer bookCount;


}
