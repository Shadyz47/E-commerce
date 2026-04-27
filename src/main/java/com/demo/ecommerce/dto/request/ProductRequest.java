package com.demo.ecommerce.dto.request;

import com.demo.ecommerce.entity.Currency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
public record ProductRequest(

        @NotBlank(message = "product name must not be blank !")
        String name,

        @NotBlank(message = "product description must not be blank !!!")
        String description,

        @Min(value = 1, message = "price must be greater than or equal to 1 !")
        Double price,

        @Min(value = 1, message = "quantity must be greater than or equal to 1 !")
        Integer quantity,

        @NotNull(message = "Currency not be null !")
        Currency currency,

        @NotNull(message = "status not be null !")
        Integer status,

        @NotBlank(message = "categoryName not be null !")
        String categoryName,

        List<MultipartFile> files
) {
}
