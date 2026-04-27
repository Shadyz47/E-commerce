package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="product-images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage {

    public static final int MAX_IMAGES_PER_PRODUCT = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="image_url", length=300)
    private String imageUrl;

    @Column(name="image_type")
    private String imageType;

    @Lob
    @Column(name="image_data",columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
