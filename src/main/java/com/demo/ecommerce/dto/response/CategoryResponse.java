package com.demo.ecommerce.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CategoryResponse(

        Long id,
        String name,
        List<ProductResponse> products
) {
}
