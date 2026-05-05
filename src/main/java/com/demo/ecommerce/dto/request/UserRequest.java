package com.demo.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;

@Builder
public record UserRequest(

        @NotBlank(message = "Please fill your fullname")
        String fullName,

        @NotBlank(message = "Please fill your username")
        String userName,
        String password,
        String phoneNumber,
        String email,
        Date dateOfBirth
) {
}
