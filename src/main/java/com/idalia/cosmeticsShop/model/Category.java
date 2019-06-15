package com.idalia.cosmeticsShop.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="category" )
@Data
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
}
