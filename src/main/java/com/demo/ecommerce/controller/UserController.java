package com.demo.ecommerce.controller;

import com.demo.ecommerce.common.ApiResponse;
import com.demo.ecommerce.common.BaseController;
import com.demo.ecommerce.dto.request.UserLoginRequest;
import com.demo.ecommerce.dto.request.UserRequest;
import com.demo.ecommerce.dto.response.UserResponse;
import com.demo.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest){

        UserResponse userReponse = userService.register(userRequest);

        return new ResponseEntity<>(userReponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserLoginRequest userLoginRequest){

        String token = userService.login(userLoginRequest);
        return new ResponseEntity<>(ApiResponse.loginSuccess(token), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteUserId(@PathVariable Long id){

        userService.deleteUserById(id);

        return new ResponseEntity<>("User with id " + id + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){

        List<UserResponse> userResponse = userService.getAllUsers();

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){

        UserResponse user = userService.getUser(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
