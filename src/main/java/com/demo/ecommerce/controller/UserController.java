package com.demo.ecommerce.controller;

import com.demo.ecommerce.dto.request.UserLoginRequest;
import com.demo.ecommerce.dto.request.UserRequest;
import com.demo.ecommerce.dto.response.UserResponse;
import com.demo.ecommerce.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest){

        UserResponse userReponse = userService.register(userRequest);

        return new ResponseEntity<>(userReponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest userLoginRequest){

        userService.login(userLoginRequest);
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
}
