package com.demo.ecommerce.controller;

import com.demo.ecommerce.common.ApiResponse;
import com.demo.ecommerce.common.BaseController;
import com.demo.ecommerce.dto.request.OrderDetailRequest;
import com.demo.ecommerce.dto.request.OrderRequest;
import com.demo.ecommerce.dto.response.OrderResponse;
import com.demo.ecommerce.entity.Order;
import com.demo.ecommerce.service.OrderService;
import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

//        var authen = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authen.getPrincipal());
//        authen.getAuthorities().forEach(System.out::println);

        return new ResponseEntity<>(new ApiResponse<>(200, "Success", orderService.getAllOrders()), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderByUserId(@PathVariable Long userId){

        return new ResponseEntity<>(new ApiResponse<>(200, "Success", orderService.getOrderByUserId(userId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> placeOrder(@Valid @RequestBody OrderRequest  orderRequest){
        OrderResponse orderRp = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(new ApiResponse<>(201, "Order Created", orderRp), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderRequest orderRequest){

        orderService.updateOrder(id, orderRequest);
        return new ResponseEntity<>(new ApiResponse<>(200, "Order Updated", null), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteOrder(@PathVariable Long id){

        orderService.deleteOrder(id);

        return  new ResponseEntity<>(new ApiResponse<>(200, "Order Deleted", null), HttpStatus.OK);
    }

}
