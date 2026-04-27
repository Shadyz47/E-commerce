package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.request.CategoryRequest;
import com.demo.ecommerce.entity.Category;

public interface CategoryService {

    void createCategory(CategoryRequest categoryRequest);

    void updateCategory(Long id, CategoryRequest categoryRequest);

    void deleteCategory(Long id);

    Category getCategoryById(Long id);
}
