package com.demo.ecommerce.repository;

import com.demo.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByRoleId(Long roleId);

    Optional<User> findByUserName(String userName);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.role")
    List<User> findAllWithQuery();
}
