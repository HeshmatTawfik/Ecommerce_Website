package com.idalia.cosmeticsShop.service;

import com.idalia.cosmeticsShop.model.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);
    Order findById(int id);
    List<Order> findByUserIdAndStat(int id,int state);
    List<Order> findAllOrdersByState(int state);

}
