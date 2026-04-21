package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.request.ProductRequest;
import com.demo.ecommerce.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getProducts();

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    void deteleProduct(Long id);
}
