package com.demo.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name", nullable = false)
    private String roleName;

    public static String ADMIN = "ADMIN";
    public static String USER = "USER";
}
