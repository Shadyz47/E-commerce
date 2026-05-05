package com.demo.ecommerce.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record OrderRequest(

        @NotBlank(message = "you must fill customer name")
        String customerName,

        @Email(message = "email should be valid")
        String email,

        String phoneNumber,

        @Size(max = 250, message = "address must be less than or equal to 250 characters")
        @NotBlank(message = "you must fill address")
        String address,

        @Size(max = 250, message = "note must be less than or equal to 250 characters")
        String note,

        @NotBlank(message = "you must fill payment method")
        String paymentMethod,

        @NotNull(message = "you must fill user id")
        @Min(value = 1, message = "user id must be greater than or equal to 1")
        Long userId,

        List<OrderDetailRequest> orderDetails
) {
}
