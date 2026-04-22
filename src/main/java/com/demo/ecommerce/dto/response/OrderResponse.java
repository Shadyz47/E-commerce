package com.demo.ecommerce.dto.response;

import com.demo.ecommerce.entity.OrderStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record OrderResponse(
        Long id,
        String customerName,
        String email,
        String phoneNumber,
        String address,
        String note,
        LocalDate orderDate,
        OrderStatus status,
        String paymentMethod,

        List<OrderDetailResponse> orderDetails
) {
}
