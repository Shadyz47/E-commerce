package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.request.ProductRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getProducts();

    ProductResponse updateProduct(Long id, ProductRequest productRequest);

    void deteleProduct(Long id);

    Product getProductById(Long id);

    void uploadImage(Long id, List<MultipartFile> files) throws IOException;

    boolean existsByName(String productName);
}
