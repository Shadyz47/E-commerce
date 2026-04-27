package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.request.UserLoginRequest;
import com.demo.ecommerce.dto.request.UserRequest;
import com.demo.ecommerce.dto.response.UserResponse;

public interface UserService {
    UserResponse register(UserRequest userRequest);

    void login(UserLoginRequest userLoginRequest);
}
