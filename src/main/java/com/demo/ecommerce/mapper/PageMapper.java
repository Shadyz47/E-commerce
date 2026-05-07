package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.response.PageResponse;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class PageMapper {

    @Autowired
    protected ProductMapper productMapper;

    public PageResponse<ProductResponse> toPagedResponse(Page<Product> page) {

        List<ProductResponse> content = page.getContent().stream().map(productMapper::toResponse).toList();

        if (page.getNumber() + 1 > page.getTotalPages()) {
            throw new RuntimeException("Page number exceeds total pages");
        }

        return new PageResponse<>(
                content,
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.isEmpty()
        );
    }
}
