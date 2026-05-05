package com.demo.ecommerce.service;

import com.demo.ecommerce.dto.request.UserLoginRequest;
import com.demo.ecommerce.dto.request.UserRequest;
import com.demo.ecommerce.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse register(UserRequest userRequest);

    String login(UserLoginRequest userLoginRequest);

    void deleteUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long id);
}
