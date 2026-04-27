package com.demo.ecommerce.dto.request;

import lombok.Builder;

import java.util.Date;

@Builder
public record UserRequest(

        String fullName,
        String userName,
        String password,
        String phoneNumber,
        String email,
        Date dateOfBirth,
        Long roleId
) {
}
