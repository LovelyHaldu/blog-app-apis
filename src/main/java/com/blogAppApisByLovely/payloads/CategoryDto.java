package com.blogAppApisByLovely.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto  {

    private Integer categoryId;
    @NotBlank
    @Size(min = 4,message = "min size must be 4 ")
    private String categoryTitle;
    @NotEmpty
    @Size(min = 10,message="Min size must be 10")
    private String categoryDescription;
}
