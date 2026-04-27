package com.demo.ecommerce.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record OrderDetailRequest(

        Long productId,
        Integer quantity
) {
}
