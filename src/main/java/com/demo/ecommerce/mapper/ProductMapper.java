package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.request.ProductRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Category;
import com.demo.ecommerce.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);

    @Mapping(source = "categoryName", target = "category.name")
    Product toEntity(ProductRequest productRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateEntityFromDto(ProductRequest productRequest, @MappingTarget Product product);

}
