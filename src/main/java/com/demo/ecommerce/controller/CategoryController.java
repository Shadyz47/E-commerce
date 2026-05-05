package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.request.CategoryRequest;
import com.demo.ecommerce.dto.response.CategoryResponse;
import com.demo.ecommerce.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest){

        categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok("Category created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequest categoryRequest){

        categoryService.updateCategory(id,categoryRequest);

        return ResponseEntity.ok("Category updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){

        categoryService.deleteCategory(id);

        return ResponseEntity.ok("Category deleted successfully with id" + id);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){

        return null;
    }
}
