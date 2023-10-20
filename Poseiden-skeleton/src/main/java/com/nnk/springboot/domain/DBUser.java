package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "db_user")
@Getter
@Setter
public class DBUser {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    @Column
    @NotBlank(message = "Role is mandatory")
    private String role;
}

