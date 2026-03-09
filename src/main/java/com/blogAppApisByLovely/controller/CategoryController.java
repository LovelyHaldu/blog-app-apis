package com.blogAppApisByLovely.controller;

import com.blogAppApisByLovely.payloads.ApiResponse;
import com.blogAppApisByLovely.payloads.CategoryDto;
import com.blogAppApisByLovely.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categDto ){
        CategoryDto createCategory = this.categoryService.createCategory(categDto);
//        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
  return ResponseEntity.status(HttpStatus.CREATED).body(createCategory);//another way of writing
    }

    //update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categDto,@PathVariable("catId") Integer categoryId ){
        CategoryDto updateCategory = this.categoryService.updateCategory(categDto,categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(updateCategory);
    }

    //delete
     @DeleteMapping("/{CatId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("CatId") Integer categoryId)
     //@PathVariable("CatId")---> categoryId is same as CatId
     {
         this.categoryService.deleteCategory(categoryId);
//         return ResponseEntity.status(HttpStatus.OK).body(null);
         return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully",true),HttpStatus.OK);
     }
    //get
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Integer catId){
        CategoryDto categoryDto = this.categoryService.getCategory(catId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }
    @GetMapping("/")
    //getAll
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categories = this.categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);

    }

}
