package com.idalia.cosmeticsShop.model;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "orderdetails")
public class OrderDetails {
    /**
     * id int not null auto_increment,
     * orderid int default null,
     * productid int default null,
     * detialamount int not  null,
     * detailprice double not null,
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "orderid")
    private int orderId;
    @Column(name = "productid")
    private int productId;
    @Column(name = "detialamount")
    private int detailAmount;
    @Column(name = "detailprice")
    private double detailPrice;
    @OneToOne
    @JoinColumn(name = "productid",insertable = false,updatable = false)
    private Product product;

    public Product getProduct(){
        this.product.setQuantity(this.detailAmount);
        return this.product;
    }



}
