package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductImage,Long> {
    int countByProductId(Long id);
}
