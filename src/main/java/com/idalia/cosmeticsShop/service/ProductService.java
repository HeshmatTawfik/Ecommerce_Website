package com.idalia.cosmeticsShop.service;

import com.idalia.cosmeticsShop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById (int id);
    void save(Product product);
    List<Product> productsOfOrder(int id);



}
