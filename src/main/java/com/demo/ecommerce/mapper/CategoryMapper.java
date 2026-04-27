package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.request.CategoryRequest;
import com.demo.ecommerce.dto.response.CategoryResponse;
import com.demo.ecommerce.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryResponse toResponse(Category category);
    List<CategoryResponse> toResponseList(List<Category> categories);

    Category toEntity(CategoryRequest categoryRequest);

    @Mapping(target = "id", ignore = true)
    void updateCategoryFromDto(CategoryRequest categoryRequest, @MappingTarget Category category);
}
