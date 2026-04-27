package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.request.CategoryRequest;
import com.demo.ecommerce.entity.Category;
import com.demo.ecommerce.mapper.CategoryMapper;
import com.demo.ecommerce.repository.CategoryRepo;
import com.demo.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;
import com.demo.ecommerce.utils.CategoryUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryMapper categoryMapper) {
        this.categoryRepo = categoryRepo;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category getCategoryById(Long id){
        return categoryRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Category not found with id: " + id));
    }

    @Override
    public void createCategory(CategoryRequest categoryRequest) {

        Category category = categoryMapper.toEntity(categoryRequest);

        categoryRepo.save(category);
    }

    @Override
    public void updateCategory(Long id, CategoryRequest categoryRequest) {

        Category category = getCategoryById(id);

        categoryMapper.updateCategoryFromDto(categoryRequest, category);

        categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(Long id) {

        Category category = getCategoryById(id);

        categoryRepo.delete(category);
    }
}
