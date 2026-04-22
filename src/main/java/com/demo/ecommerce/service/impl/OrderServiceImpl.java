package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.request.OrderRequest;
import com.demo.ecommerce.dto.response.OrderResponse;
import com.demo.ecommerce.entity.Order;
import com.demo.ecommerce.entity.OrderDetail;
import com.demo.ecommerce.entity.OrderStatus;
import com.demo.ecommerce.entity.Product;
import com.demo.ecommerce.mapper.OrderMapper;
import com.demo.ecommerce.repository.OrderRepo;
import com.demo.ecommerce.repository.ProductRepo;
import com.demo.ecommerce.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final ProductRepo productRepo;

    public OrderServiceImpl(OrderRepo orderRepo, OrderMapper orderMapper, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
        this.productRepo = productRepo;
    }


    @Override
    public List<OrderResponse> getAllOrders() {

        List<Order> orders = orderRepo.findAll();

        return orders.stream().map(orderMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderMapper.toEntity(orderRequest);

        order.setOrderDate(java.time.LocalDate.now());
        order.setStatus(OrderStatus.PENDING);

        for(OrderDetail orderDetail : order.getOrderDetails()) {

            Long productId = orderDetail.getProduct().getId();
            Integer quantity = orderDetail.getTotalQuantity();

            Product productDb = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            if(productDb.getQuantity() < orderDetail.getProduct().getQuantity()) {
                throw new RuntimeException("Product quantity not enough");
            }

            productDb.setQuantity(productDb.getQuantity() - quantity);

            orderDetail.setProduct(productDb);
            orderDetail.setOrder(order);

            orderDetail.setTotalPrice(productDb.getPrice() * quantity);
        }

        Order savedOrder = orderRepo.save(order);

        return orderMapper.toResponse(savedOrder);
    }
}
