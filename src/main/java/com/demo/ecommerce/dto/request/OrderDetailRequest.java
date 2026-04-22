package com.demo.ecommerce.dto.request;

import lombok.Builder;

@Builder
public record OrderDetailRequest(
        Long productId,
        Integer quantity
) {
}
