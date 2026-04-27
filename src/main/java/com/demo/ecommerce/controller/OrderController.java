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

        return new ResponseEntity<>(new ApiResponse<>(200, "Success", orderService.getAllOrders()), HttpStatus.OK);
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

//    @PostMapping("/generateFakeOrders")
//    public ResponseEntity<String> generateFakeOrder(){
//
//        Faker faker = new Faker();
//
//        for(int i=0;i<5;i++){
//
//            List<OrderDetailRequest> orderDetailRequests = new ArrayList<>();
//
//            for(int j=0;j<(int)(Math.random()*5+1);j++){
//                OrderDetailRequest orderDetailRequest = OrderDetailRequest.builder()
//                        .productId((long)(Math.random()*10+1))
//                        .quantity(faker.number().numberBetween(1, 10))
//                        .build();
//
//                orderDetailRequests.add(orderDetailRequest);
//            }
//
//            OrderRequest orderRequest = OrderRequest.builder()
//                    .customerName(faker.name().fullName())
//                    .email(faker.internet().emailAddress())
//                    .phoneNumber(faker.phoneNumber().phoneNumber())
//                    .address(faker.address().fullAddress())
//                    .note(faker.lorem().sentence())
//                    .paymentMethod(faker.options().option("Credit Card", "PayPal", "Cash)"))
//                    .orderDetails(orderDetailRequests)
//                    .build();
//
//            try {
//                orderService.placeOrder(orderRequest);
//            }
//            catch (Exception e){
//                return ResponseEntity.badRequest().body(e.getMessage());
//            }
//        }
//
//        return  ResponseEntity.ok().body("Fake orders generated successfully");
//    }
}
