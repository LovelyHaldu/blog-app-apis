package com.blogAppApisByLovely.services;

import com.blogAppApisByLovely.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //By default, method is public we don't need to explicitly make a public

    //Create
    CategoryDto createCategory(CategoryDto categoryDto);

    //Update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId );

    //delete
    void deleteCategory(Integer categoryId);

    //get
    CategoryDto getCategory(Integer categoryId);

    //getAll
    List<CategoryDto> getAllCategories();
}
