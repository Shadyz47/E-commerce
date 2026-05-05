package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o " +
            "LEFT JOIN FETCH o.orderDetails " +
            "LEFT JOIN FETCH o.user ")
    List<Order> findAllWithQuery();

    Optional<Order> findByUserId(Long userId);
}
