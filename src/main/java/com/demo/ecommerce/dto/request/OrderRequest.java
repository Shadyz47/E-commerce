package com.demo.ecommerce.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

        List<OrderDetailRequest> orderDetails
) {
}
