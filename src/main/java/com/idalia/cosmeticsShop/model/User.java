package com.idalia.cosmeticsShop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "Password is required")
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    @Email
    @NotBlank(message = "email is required")
    private String email;
    @Column(name = "phone")
    @NotBlank(message = "phone is required")
    private String phone;
    @Column(name = "city")
    @NotBlank(message = "city is required")
    private String city;
    @Column(name = "street")
    @NotBlank(message = "Street is required")
    private String street;
    @Column(name = "postalcode")
    @NotBlank(message = "postal code is required")
    private String postalCode;
    @Column(name = "authorityid")
    private int role;

    @Transient
    Order order;
   /* @OneToMany(mappedBy = "user")
    private List<Order> order;*/

    public User(String username, String password, String email, int role) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setRole(role);
    }

    public User() {
    }



}
