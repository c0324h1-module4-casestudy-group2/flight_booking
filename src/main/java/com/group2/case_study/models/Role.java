package com.group2.case_study.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotBlank
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    // Constructors, getters, and setters

    public Role() {
    }

    public Role(Long id, @NotBlank String name, @NotBlank String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
