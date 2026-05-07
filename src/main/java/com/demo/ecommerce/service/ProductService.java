package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.request.ProductRequest;
import com.demo.ecommerce.dto.response.PageResponse;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    PageResponse<ProductResponse> getProducts(Pageable pageable, String name);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    void deteleProduct(Long id);

    Product getProductById(Long id);

    void uploadImage(Long id, List<MultipartFile> files) throws IOException;

    boolean existsByName(String productName);
}
