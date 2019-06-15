package com.idalia.cosmeticsShop.service;

import com.idalia.cosmeticsShop.model.OrderDetails;
import com.idalia.cosmeticsShop.model.Product;

import java.util.List;

public interface OrderDetailsService {
    void save(List<Product> products,int OrderId);
    List<OrderDetails> findAllByOrderId(int orderId);
    int quantity(int orderId,int productId );


}
