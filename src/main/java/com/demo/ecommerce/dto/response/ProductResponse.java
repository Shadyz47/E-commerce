package com.demo.ecommerce.dto.response;

import com.demo.ecommerce.entity.Category;
import com.demo.ecommerce.entity.Currency;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        Integer quantity,
        Currency currency,
        String categoryName,
        List<String> imageUrls
) {
}
