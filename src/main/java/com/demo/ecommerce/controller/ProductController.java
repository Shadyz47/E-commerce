package com.demo.ecommerce.controller;

import com.demo.ecommerce.common.ApiResponse;
import com.demo.ecommerce.common.BaseController;
import com.demo.ecommerce.dto.request.ProductRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Currency;
import com.demo.ecommerce.entity.Product;
import com.demo.ecommerce.mapper.ProductMapper;
import com.demo.ecommerce.service.ProductService;
import com.github.javafaker.Faker;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController extends BaseController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
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

    @PostMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(
            @PathVariable Long id,
            @RequestPart("files") List<MultipartFile> files) throws IOException {

        try {
            productService.uploadImage(id, files);
            return new ResponseEntity<>(ApiResponse.success("Image uploaded successfully"), HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id){

        Product p = productService.getProductById(id);
        ProductResponse productResponse = productMapper.toResponse(p);

        return new ResponseEntity<>(foundResponse(productResponse), HttpStatus.OK);
    }

    //khoi tao fake product
    @PostMapping("/generateFakeProducts")
    public ResponseEntity<String>  generateFakeProducts(){

        Faker faker = new Faker();
        for(int i=0;i<5;i++){
            String productName = faker.commerce().productName();

            if(productService.existsByName(productName)){
                continue;
            }

            ProductRequest productRequest = ProductRequest.builder()
                    .name(productName)
                    .description(faker.lorem().sentence())
                    .price(Double.parseDouble(faker.commerce().price()))
                    .quantity(faker.number().numberBetween(1, 100))
                    .currency(Currency.valueOf(faker.options().option("USD", "EUR", "VND")))
                    .status(1)
                    .categoryName(faker.commerce().department())
                    .build();

            try{
                productService.createProduct(productRequest);
            }
            catch(Exception e){
                return ResponseEntity.badRequest().body("Failed to generate fake products: " + e.getMessage());
            }
        }

        return new ResponseEntity<>("product has been generated successfully", HttpStatus.OK);
    }
}
