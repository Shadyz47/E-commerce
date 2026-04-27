package com.demo.ecommerce.dto.request;

import lombok.Builder;

@Builder
public record UserLoginRequest(
        String userName,
        String password
) {
}
