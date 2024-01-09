package com.riservi.technicaltest.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Table(name = "users")
@Entity(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "users", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roles", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

}
