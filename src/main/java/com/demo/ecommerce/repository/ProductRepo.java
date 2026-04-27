package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.category WHERE p.status=1")
    List<Product> findAllWithQuery();

    boolean existsByName(String productName);
}
