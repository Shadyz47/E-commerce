package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.request.OrderDetailRequest;
import com.demo.ecommerce.dto.response.OrderDetailResponse;
import com.demo.ecommerce.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderDetailMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "productPrice")
    @Mapping(source = "totalQuantity", target = "productQuantity")
    @Mapping(source = "totalPrice", target = "totalProductPrice")
    OrderDetailResponse toResponse(OrderDetail orderDetail);


    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "quantity", target = "totalQuantity")
    OrderDetail toEntity(OrderDetailRequest orderDetailRequest);
}
