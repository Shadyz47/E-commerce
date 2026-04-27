package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_name", length = 100, nullable = false)
    private String customerName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number",length = 10, nullable = false)
    private String phoneNumber;

    @Column(name="address")
    private String address;

    @Column(name="note")
    private String note;

    @Column(name="order_date")
    private LocalDate orderDate;

    @Column(name="status")
    private OrderStatus status;

    @Column(name="payment_method")
    private String paymentMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();
}
