package com.demo.ecommerce.service.impl;

import com.demo.ecommerce.dto.request.UserLoginRequest;
import com.demo.ecommerce.dto.request.UserRequest;
import com.demo.ecommerce.dto.response.UserResponse;
import com.demo.ecommerce.entity.Role;
import com.demo.ecommerce.entity.User;
import com.demo.ecommerce.mapper.UserMapper;
import com.demo.ecommerce.repository.RoleRepo;
import com.demo.ecommerce.repository.UserRepo;
import com.demo.ecommerce.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserResponse register(UserRequest userRequest) {

        Role role = roleRepo.findById(userRequest.roleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.password()));
        user.setRole(role);
        userRepo.save(user);

        return userMapper.toResponse(user);
    }

    @Override
    public void login(UserLoginRequest userLoginRequest) {
        User user = userRepo.findByUserName(userLoginRequest.userName()).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        if(!passwordEncoder.matches(userLoginRequest.password(),user.getPassword())) {
            throw new RuntimeException("Password is incorrect");
        }
    }
}
