package com.demo.ecommerce.dto.request;

import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record OrderRequest(
        String customerName,
        String email,
        String phoneNumber,
        String address,
        String note,
        String paymentMethod,

        List<OrderDetailRequest> orderDetails
) {
}
