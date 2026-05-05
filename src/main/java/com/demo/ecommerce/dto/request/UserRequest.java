package com.demo.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;

@Builder
public record UserRequest(

        @NotBlank(message = "Please fill your fullname")
        String fullName,

        @NotBlank(message = "Please fill your username")
        @UniqueElements(message = "Username already exists")
        String userName,

        @NotBlank(message = "Please fill your password")
        @Size(min=4, max=20, message = "Password must be between 4 and 20 characters")
        String password,
        String phoneNumber,
        String email,
        Date dateOfBirth
) {
}
