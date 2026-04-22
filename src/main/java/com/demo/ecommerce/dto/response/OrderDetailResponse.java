package com.demo.ecommerce.dto.response;

import lombok.Builder;

@Builder
public record OrderDetailResponse(
        Long id,
        Long productId,
        String productName,
        Double productPrice,
        Integer productQuantity,
        Double totalProductPrice
) {
}
