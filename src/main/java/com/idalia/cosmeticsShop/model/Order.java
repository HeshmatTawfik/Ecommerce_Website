package com.idalia.cosmeticsShop.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    /**
     * create table orders(
     * id int not null auto_increment,
     * userid int default null,
     * createddate date default null,
     * state int default 0,
     * city varchar(255) not null,
     * address varchar(255) not null,
     * totalprice double default  null,
     * primary key(id)
     * );
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userid")
    private int userId;
    @Column(name = "createddate")
    private LocalDateTime createdDate;
    @Column(name = "state")
    private int state;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @Column(name = "totalprice")
    private double totalPrice;
    @Transient
    private List<Product> products;

    public String formatDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        boolean isToday = formatter.format(this.createdDate).equals(formatter.format(LocalDateTime.now()));

        if (isToday) {
            return formatter.format(this.createdDate)+ " (Today)";
        }
        return formatter.format(this.createdDate);
    }

   /* @OneToMany(mappedBy ="product")
    private List<Product> products;*/
 /*   @OneToMany(mappedBy ="OrderDetails")
    private List <OrderDetails> orderDetails;*/


}
