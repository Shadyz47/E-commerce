package com.demo.ecommerce.controller;

import com.demo.ecommerce.common.ApiResponse;
import com.demo.ecommerce.common.BaseController;
import com.demo.ecommerce.dto.request.OrderRequest;
import com.demo.ecommerce.dto.response.OrderResponse;
import com.demo.ecommerce.entity.Order;
import com.demo.ecommerce.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrders(){

        return new ResponseEntity<>(new ApiResponse<>(200, "Success", orderService.getAllOrders()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> placeOrder(@RequestBody OrderRequest  orderRequest){
        OrderResponse orderRp = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(new ApiResponse<>(201, "Order Created", orderRp), HttpStatus.CREATED);
    }
}
