package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.request.ProductRequest;
import com.demo.ecommerce.dto.response.ProductResponse;
import com.demo.ecommerce.entity.Category;
import com.demo.ecommerce.entity.Product;
import com.demo.ecommerce.entity.ProductImage;
import com.demo.ecommerce.mapper.ProductMapper;
import com.demo.ecommerce.repository.CategoryRepo;
import com.demo.ecommerce.repository.ProductImageRepo;
import com.demo.ecommerce.repository.ProductRepo;
import com.demo.ecommerce.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final CategoryRepo categoryRepo;
    private final ProductImageRepo productImageRepo;

    public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper, CategoryRepo categoryRepo, ProductImageRepo productImageRepo) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
        this.categoryRepo = categoryRepo;
        this.productImageRepo = productImageRepo;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found !"));
    }

    @Override
    @Transactional
    public void uploadImage(Long id, List<MultipartFile> files) throws IOException {
        Product productDb = getProductById(id);

        if(files == null || files.isEmpty()) throw new RuntimeException("Product needs image!");

        int productImgCount = productImageRepo.countByProductId(id);
        if(productImgCount > ProductImage.MAX_IMAGES_PER_PRODUCT){
            throw new RuntimeException("Product image max is 5");
        }

        for(MultipartFile file : files) {
           if(file == null || file.isEmpty()) continue;

           ProductImage productImage = ProductImage.builder()
                   .imageUrl(file.getOriginalFilename())
                   .imageType(file.getContentType())
                   .imageData(file.getBytes())
                   .product(productDb)
                   .build();

           productImageRepo.save(productImage);
        }
    }

    @Override
    public boolean existsByName(String productName) {
        return productRepo.existsByName(productName);
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
        Product existedProduct = getProductById(id);

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
        Product existedProduct = getProductById(id);

//        productRepo.delete(existedProduct);       //xoa truc tiep
        existedProduct.setStatus(0);                //xoa mem
        productRepo.save(existedProduct);
    }


}
