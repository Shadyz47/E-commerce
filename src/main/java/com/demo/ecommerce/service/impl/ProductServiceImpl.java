package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.request.ProductRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Category;
import com.demo.ecommerce.entity.Product;
import com.demo.ecommerce.mapper.ProductMapper;
import com.demo.ecommerce.repository.CategoryRepo;
import com.demo.ecommerce.repository.ProductRepo;
import com.demo.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final CategoryRepo categoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<ProductResponse> getProducts() {
        List<Product> products = productRepo.findAllWithQuery();

        return products.stream().map(productMapper::toResponse).toList();
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = productMapper.toEntity(productRequest);

        if(productRequest.categoryName() != null){
             Category category = categoryRepo.findByName(productRequest.categoryName())
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(productRequest.categoryName());
                        return categoryRepo.save(newCategory);
                    });

             product.setCategory(category);
        }

        productRepo.save(product);

        return productMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product existedProduct = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found !"));

        productMapper.updateEntityFromDto(productRequest, existedProduct);

        if(productRequest.categoryName() != null){
            Category category = categoryRepo.findByName(productRequest.categoryName())
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setName(productRequest.categoryName());
                        return categoryRepo.save(newCategory);
                    });

            existedProduct.setCategory(category);
        }

        productRepo.save(existedProduct);

        return productMapper.toResponse(existedProduct);
    }

    @Override
    public void deteleProduct(Long id) {
        Product existedProduct = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found !"));

//        productRepo.delete(existedProduct);       //xoa truc tiep
        existedProduct.setStatus(0);                //xoa mem
        productRepo.save(existedProduct);
    }
}
