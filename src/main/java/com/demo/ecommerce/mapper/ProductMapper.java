package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.request.ProductRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Product;
import com.demo.ecommerce.entity.ProductImage;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "images", target = "imageUrls", qualifiedByName = "mapImageUrls")
    ProductResponse toResponse(Product product);

    @Mapping(source = "categoryName", target = "category.name")
    Product toEntity(ProductRequest productRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    void updateEntityFromDto(ProductRequest productRequest, @MappingTarget Product product);

    @Named("mapImageUrls")
    default List<String> mapImageUrls(List<ProductImage> imageUrls) {
        if(imageUrls == null) return List.of();

        return imageUrls.stream()
                .map(ProductImage::getImageUrl)
                .toList();
    }
}
