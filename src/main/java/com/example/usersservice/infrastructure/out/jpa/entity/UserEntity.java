package com.example.usersservice.infrastructure.out.jpa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String userLastName;
    private String documentNumber;
    private String phone;
    private LocalDate birthdate;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

}