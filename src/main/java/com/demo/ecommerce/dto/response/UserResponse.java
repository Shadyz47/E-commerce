package com.demo.ecommerce.dto.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record UserResponse(
        Long id,
        String fullName,
        String userName,
        String phoneNumber,
        String email,
        Date dateOfBirth,
        String roleName
) {
}
