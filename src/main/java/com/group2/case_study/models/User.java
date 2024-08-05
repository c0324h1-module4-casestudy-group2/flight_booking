package com.group2.case_study.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles = new HashSet<Role>();

    @NotBlank
    @Column(name = "fullname", length = 200, nullable = false)
    private String fullName;

    @NotBlank
    @Column(name = "username", length = 200, nullable = false)
    private String username;

    @NotBlank
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @NotBlank
    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "phone", length = 12, nullable = false)
    private String phone;

    @Column(name = "avatar", columnDefinition = "text")
    private String avatar;

    @Column(name = "activated")
    private Boolean activated;

    @Column(name = "remember_token", length = 255)
    private String rememberToken;

    // Constructors, getters, and setters

    public User() {
    }

    public User(Integer id, @NotBlank String fullName, @NotBlank String username, @NotBlank String password,
                @NotBlank String email, String address, String phone, String avatar,
                Boolean activated, String rememberToken) {
        super();
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.activated = activated;
        this.rememberToken = rememberToken;
    }

    public User(@NotBlank String fullName, @NotBlank String username, @NotBlank String password,
                @NotBlank String email, String address, String phone, String avatar,
                Boolean activated, String rememberToken) {
        super();
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.avatar = avatar;
        this.activated = activated;
        this.rememberToken = rememberToken;
    }
}
