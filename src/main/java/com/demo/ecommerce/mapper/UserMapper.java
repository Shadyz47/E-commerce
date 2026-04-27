package com.demo.ecommerce.mapper;

import com.demo.ecommerce.dto.request.UserRequest;
import com.demo.ecommerce.dto.response.UserResponse;
import com.demo.ecommerce.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(source = "role.roleName", target = "roleName")
    UserResponse toResponse(User user);

    @Mapping(source = "roleId", target = "role.id")
    User toEntity(UserRequest userRequest);
}
