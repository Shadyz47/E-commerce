package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByRoleId(Long roleId);

    Optional<User> findByUserName(String s);
}
