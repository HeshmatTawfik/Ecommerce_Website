package com.idalia.cosmeticsShop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "product")
public class Product {
    /**id int not null auto_increment,
     name  varchar(255) not null,
     price double not null,
     picture varchar(255) default 'noimage',
     categoryid int default null,
     discount double default 0.0,
     primary key(id)*/
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotBlank
    private String name;
    @Column (name = "price")
    @NotNull(message = "put price ")
    private double price;
    @Column (name = "picture")
    private String pictureUrl;
    @Column(name = "categoryid")
    private int categoryId;
    @Column(name = "discount")
    @NotNull(message = "put discount ")
    private double discount;
    @Column(name = "description")
    @NotBlank
    private String description;
    @Transient
    @NotBlank
    private String categoryName;
    @Transient
     private int quantity;
    @Transient
    @OneToOne(mappedBy = "product")
    private OrderDetails orderDetails;



}
