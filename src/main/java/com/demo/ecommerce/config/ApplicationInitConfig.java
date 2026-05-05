package com.demo.ecommerce.config;

import com.demo.ecommerce.entity.Role;
import com.demo.ecommerce.entity.User;
import com.demo.ecommerce.repository.RoleRepo;
import com.demo.ecommerce.repository.UserRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationInitConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public ApplicationInitConfig(PasswordEncoder passwordEncoder, UserRepo userRepo, RoleRepo roleRepo) {
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepo userRepo) {
        return args -> {

            Role role = roleRepo.findByRoleName(Role.ADMIN).orElseThrow(
                    () -> new RuntimeException("Role not found")
            );

            userRepo.save(User.builder()
                    .userName("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(role)
                    .build());
        };
    }
}
