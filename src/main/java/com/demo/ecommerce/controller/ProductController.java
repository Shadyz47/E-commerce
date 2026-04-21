package com.demo.ecommerce.controller;

import com.demo.ecommerce.common.ApiResponse;
import com.demo.ecommerce.common.BaseController;
import com.demo.ecommerce.dto.request.ProductRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController extends BaseController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProducts(){

        return new ResponseEntity<>(foundResponse(productService.getProducts()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@Valid @RequestBody ProductRequest productRequest){

        ProductResponse productResponse = productService.createProduct(productRequest);

        return new ResponseEntity<>(ApiResponse.success(productResponse), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){

        ProductResponse productResponse = productService.updateProduct(id, productRequest);

        return new ResponseEntity<>(ApiResponse.success(productResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id){

        productService.deteleProduct(id);

        return new ResponseEntity<>(ApiResponse.success("Product with id " + id + " deleted successfully"), HttpStatus.OK);
    }
}
