package com.riservi.technicaltest.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "roles")
@Entity(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
