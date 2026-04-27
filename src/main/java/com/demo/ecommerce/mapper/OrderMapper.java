package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.request.OrderRequest;
import com.demo.ecommerce.dto.response.OrderResponse;
import com.demo.ecommerce.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrderDetailMapper.class})
public interface OrderMapper {

    OrderResponse toResponse(Order order);
    List<OrderResponse> toResponseList(List<Order> orders);

    Order toEntity(OrderRequest orderRequest);
    List<Order> toEntityList(List<OrderRequest> orderRequests);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    void updateOrderFromDto(OrderRequest orderRequest, @MappingTarget Order order);
}
