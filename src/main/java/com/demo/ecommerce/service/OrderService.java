package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.request.OrderRequest;
import com.demo.ecommerce.dto.response.OrderResponse;
import com.demo.ecommerce.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders();

    OrderResponse placeOrder(OrderRequest  orderRequest);

    void updateOrder(Long id, OrderRequest orderRequest);

    void deleteOrder(Long id);
}
