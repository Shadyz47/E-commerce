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

            if(quantity <= 0 || productDb.getQuantity() < quantity) {
                throw new RuntimeException("Product quantity not enough or quantity must be greater than 0");
            }

            productDb.setQuantity(productDb.getQuantity() - quantity);

            orderDetail.setProduct(productDb);
            orderDetail.setOrder(order);

            orderDetail.setTotalPrice(productDb.getPrice() * quantity);
        }

        Order savedOrder = orderRepo.save(order);

        return orderMapper.toResponse(savedOrder);
    }

    @Override
    @Transactional
    public void updateOrder(Long id, OrderRequest orderRequest) {

        Order existedOrder = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        existedOrder.setOrderDate(java.time.LocalDate.now());

        //hoan tra so luong
        for(OrderDetail orderDetail : existedOrder.getOrderDetails()){

            Long productId = orderDetail.getProduct().getId();
            Integer quantity = orderDetail.getTotalQuantity();

            Product productDb = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            productDb.setQuantity(productDb.getQuantity() + quantity);
        }

        orderMapper.updateOrderFromDto(orderRequest, existedOrder);

        // xoa het cac chi tiet don hang cu ---> dan den tăng ID của orderDetail
        existedOrder.getOrderDetails().clear();

        Order orderFromRq = orderMapper.toEntity(orderRequest);

        if (orderFromRq.getOrderDetails() == null || orderFromRq.getOrderDetails().isEmpty()) {
            orderRepo.save(existedOrder);
            return;
        }

        for(OrderDetail orderDetail : orderFromRq.getOrderDetails()) {

            Long productId = orderDetail.getProduct().getId();
            Integer quantity = orderDetail.getTotalQuantity();

            Product productDb = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            if(productDb.getQuantity() < quantity) {
                throw new RuntimeException("Product quantity not enough");
            }

            productDb.setQuantity(productDb.getQuantity() - quantity);

            orderDetail.setProduct(productDb);
            orderDetail.setOrder(existedOrder);

            orderDetail.setTotalPrice(productDb.getPrice() * quantity);

            existedOrder.getOrderDetails().add(orderDetail);
        }

        orderRepo.save(existedOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {

        Order existeOrder = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        for(OrderDetail orderDetail : existeOrder.getOrderDetails()){

            Product productDb = orderDetail.getProduct();
            productDb.setQuantity(productDb.getQuantity() + orderDetail.getTotalQuantity());

            productRepo.save(productDb);
        }

        existeOrder.getOrderDetails().clear();
        orderRepo.delete(existeOrder);
    }
}
