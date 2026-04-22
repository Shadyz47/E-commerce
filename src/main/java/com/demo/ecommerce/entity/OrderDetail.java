package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="order_details")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name="total_price", nullable = false)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;
}
